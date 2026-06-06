#!/bin/bash
# Copyright 2025-2026 NXP
# NXP Proprietary. This software is owned or controlled by NXP and may only be
# used strictly in accordance with the applicable license terms.  By expressly
# accepting such terms or by downloading, installing, activating and/or
# otherwise using the software, you are agreeing that you have read, and that
# you agree to comply with and are bound by, such license terms.  If you do
# not agree to be bound by the applicable license terms, then you may not
# retain, install, activate or otherwise use the software.
# ============================================================================
# Color Definitions
# ============================================================================
set -e

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[38;5;117m'
MAGENTA='\033[0;35m'
CYAN='\033[0;36m'
BOLD='\033[1m'
RESET='\033[0m'

CHECK="✓"
CROSS="✗"
ARROW="→"
WARNING="⚠"

# ============================================================================
# Utility Functions
# ============================================================================
_strip_ansi() {
	sed -E 's/\x1B\[[0-9;]*[[:alpha:]]//g'
}

_repeat_unit() {
	local __var="$1" __count="$2" __unit="$3"
	if (( __count <= 0 )); then
		printf -v "$__var" "%s" ""
		return
	fi
	local out=""
	local i
	for (( i=0; i<__count; i++ )); do
		out+="$__unit"
	done
	printf -v "$__var" "%s" "$out"
}

_repeat_char() {
	local __var="$1" __count="$2" __char="$3"
	if (( __count <= 0 )); then
		printf -v "$__var" "%s" ""
		return
	fi
	local spaces
	printf -v spaces "%*s" "$__count" ""
	local ch="${__char:0:1}"
	local val
	val=$(printf "%s" "$spaces" | LC_ALL=C tr ' ' "$ch")
	printf -v "$__var" "%s" "$val"
}

_center_visible() {
	local text="$1" width="$2" pad="${3:- }"
	local vis stripped total_pad left right L R
	stripped="$(printf "%s" "$text" | _strip_ansi)"
	vis="${#stripped}"

	(( vis < 0 )) && vis=0
	(( width < vis )) && width="$vis"

	total_pad=$(( width - vis ))
	(( total_pad < 0 )) && total_pad=0

	left=$(( total_pad / 2 ))
	right=$(( total_pad - left ))

	_repeat_char L "$left" "$pad"
	_repeat_char R "$right" "$pad"

	echo -e -n "$L" "${BOLD}${text}${RESET}" "$R"
}

header() {
	local title="$1"
	local min_width="80"
	local border_pad="═"

	if ! [[ "$min_width" =~ ^[0-9]+$ ]]; then
		printf "header: MIN_WIDTH must be an integer (got: %s)\n" "$min_width" >&2
		return 2
	fi

	local content
	content="${title}"

	local stripped visible inner_target outer_target
	stripped="$(printf "%s" "$content" | _strip_ansi)"
	visible="${#stripped}"

	outer_target=$(( visible + 2 + 2 ))
	if (( outer_target < min_width )); then
		outer_target="$min_width"
	fi

	local inner_width=$(( outer_target - 2 ))
	(( inner_width < 0 )) && inner_width=0

	local horiz
	_repeat_unit horiz "$inner_width" "$border_pad"

	printf "\n"
	echo -e "${BOLD}${CYAN}╔""$horiz""╗${RESET}"
	echo -e -n "${BOLD}${CYAN}║${RESET} "

	local content_area=$(( inner_width - 4 ))
	(( content_area < 0 )) && content_area=0

	_center_visible "${content}" "$content_area" " "

	echo -e " ${BOLD}${CYAN}║${RESET}"
	echo -e "${BOLD}${CYAN}╚""$horiz""╝${RESET}"
	printf "\n"
}

section() {
	echo -e "\n${BOLD}${BLUE}${ARROW} $1${RESET}"
	local sec
	_repeat_unit sec 80 "─"
	echo -e "${BLUE}${sec}${RESET}"
}

success() {
	echo -e " ${GREEN}${CHECK}${RESET} $1"
}

error() {
	echo -e " ${RED}${CROSS}${RESET} $1" >&2
}

info() {
	echo -e "  $1"
}

warning() {
	echo -e " ${YELLOW}${WARNING}${RESET} $1"
}

# ============================================================================
# Error Handler
# ============================================================================
cleanup_on_error() {
	local exit_code=$?
	if [ $exit_code -ne 0 ]; then
		error "Installation failed with exit code: ${exit_code}"
		info "Please check the error messages above and resolve before retrying"
	fi
}

trap cleanup_on_error ERR EXIT

# ============================================================================
# Validation Functions
# ============================================================================
validate_package_dependency() {
	local package_name="$1"

	if dpkg-query -W -f='${Status}' "$package_name" 2>/dev/null | grep -q "install ok installed"; then
		success "Dependency '${package_name}' is installed"
		return 0
	else
		error "Required dependency '${package_name}' is NOT installed"
		info "Install with: dpkg -i ${package_name}.deb"
		return 1
	fi
}

validate_file_exists() {
	local file_path="$1"
	local description="$2"

	if [ -f "$file_path" ]; then
		success "Found ${description}: ${file_path}"
		return 0
	else
		error "${description} not found: ${file_path}"
		return 1
	fi
}

validate_directory_exists() {
	local dir_path="$1"
	local description="$2"

	if [ -d "$dir_path" ]; then
		success "Found ${description}: ${dir_path}"
		return 0
	else
		error "${description} not found: ${dir_path}"
		return 1
	fi
}

# Package information
PACKAGE_NAME="Smart Device Gateway"
PACKAGE_VERSION="1.0.0"
FETCH_MODELS_SCRIPT="uvx --from /usr/share/python-wheels/fetch_models-1.0.0-py3-none-any.whl fetch_models"

# Define models to check (add more as needed)
readonly MODELS=(
	"nxp/Qwen2.5-7B-Instruct-Ara240"
)

# Display header
header "${PACKAGE_NAME} v${PACKAGE_VERSION} - Installation"
# Create Python virtual environment
section "Installing virtual enviroment"
python3 -m pip install uv

if [ ! -d "/usr/share/smart-device-gateway/venv" ]; then
	info "Creating new virtual environment..."
	uv venv /usr/share/smart-device-gateway/venv --seed
	success "Virtual environment created"
else
	info "Virtual environment already exists, reusing it"
fi

source /usr/share/smart-device-gateway/venv/bin/activate

uv pip install --no-progress /usr/share/python-wheels/smart_device_gateway-1.0.0-py3-none-any.whl
echo ""
success "Virtual enviroment installed successfully"

section "Installing espeak-ng"
/usr/share/smart-device-gateway/install_espeak-ng.sh

	# Download models automatically
	section "Fetching models from Hugging Face Hub"

	echo ""
	info "${BOLD}${YELLOW}Downloading Qwen2.5-7B-Instruct model (~8GB)...${RESET}"
	echo ""

	# Download each model
	for model in "${MODELS[@]}"; do
		info "Downloading model: ${YELLOW}${model}${RESET}"
		if ! ${FETCH_MODELS_SCRIPT} --repo-id "${model}"; then
			error "Failed to download model: ${model}"
			warning "You can manually download it later using:"
			info "  $FETCH_MODELS_SCRIPT --repo-id $model"
			# Don't exit, continue with other models
		else
			success "Downloaded: ${model}"
		fi
	done

	# Final success message
	echo ""
	header "Installation completed successfully!"
	echo ""
	success "Package ${BOLD}${PACKAGE_NAME}${RESET} installed successfully"
	info "All scripts are now available system-wide"
	echo ""
	exit 0