SUMMARY = "i.MX Camera Rotation"
DESCRIPTION = "Recipe for i.MX Video Rotation Acceleration on i.MX Platforms"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=926b61ff3cc1596590049c66b6548935"

IMX_VIDEO_ROTATION_DIR = "${GPNT_APPS_FOLDER}/scripts/multimedia/imx-camera-rotation"

NXP_IMX_VIDEO_ROTATION_SRC ?= "git://github.com/nxp-imx-support/imx-camera-rotation.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "a4cb6559cff20ed4a6056b6f0f4364bbf008037d"

SRC_URI = "${NXP_IMX_VIDEO_ROTATION_SRC};branch=${SRCBRANCH}"
S = "${WORKDIR}/git"

DEPENDS = "\
        qtbase \
        qtdeclarative \
        qtdeclarative-native \
        opencv \
        wayland-native \
        wayland-protocols \
"

inherit pkgconfig qt6-cmake

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DCMAKE_EXPORT_COMPILE_COMMANDS=ON"

# Skip buildpaths QA check for this package
INSANE_SKIP:${PN} += "ldflags buildpaths"
INSANE_SKIP:${PN}-dbg += "ldflags buildpaths"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_compile() {
    # Build main application using cmake (handled by qt6-cmake inherit)
    cmake_do_compile
    
    # Build demos using make
    if [ -d "${S}/demos" ]; then
        cd ${S}/demos
        oe_runmake
    fi
}

do_install() {
    install -d ${D}${IMX_VIDEO_ROTATION_DIR}
    
    # Install main application from cmake build
    if [ -f "${B}/camera_rotation" ]; then
        install -m 0755 ${B}/camera_rotation ${D}${IMX_VIDEO_ROTATION_DIR}
    fi
    
    # Install demos
    if [ -d "${S}/demos" ]; then
        install -d ${D}${IMX_VIDEO_ROTATION_DIR}/demos
        
        # Install built demo binaries
        for demo_dir in ${S}/demos/*/; do
            if [ -d "$demo_dir" ]; then
                demo_name=$(basename "$demo_dir")
                if [ -f "$demo_dir/$demo_name" ]; then
                    install -m 0755 "$demo_dir/$demo_name" ${D}${IMX_VIDEO_ROTATION_DIR}/demos/
                fi
            fi
        done
    fi
}

FILES:${PN} += "${IMX_VIDEO_ROTATION_DIR}"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"