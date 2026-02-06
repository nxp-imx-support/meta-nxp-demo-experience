SUMMARY = "i.MX Wi-Fi Selector"
DESCRIPTION = "Recipe for i.MX Wi-Fi selector based on LVGL for i.MX Platforms"
SECTION = "Communication"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d8ff2d641cc45adce1b1882be29d1e35"

IMX_WIFI_SELECTOR_DIR = "${GPNT_APPS_FOLDER}/scripts/communication/wifi_selector"

NXP_IMX_WIFI_SELECTOR_SRC ?= "git://github.com/nxp-imx-support/imx-wifi-selector.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "add8dd7d6f060db204a7aaf1b548877a72574ded"

SRC_URI = "${NXP_IMX_WIFI_SELECTOR_SRC};branch=${SRCBRANCH}"

DEPENDS =  "wayland \
            wayland-native \
            wayland-protocols \
            libxkbcommon \
            pkgconfig \
            xdg-utils \
            curl"

inherit cmake pkgconfig

do_configure() {
    # Build wayland protocols library
    rm -rf ${S}/build
    mkdir ${S}/build
    cd ${S}/ports/linux/lv_drivers/wayland/
    cmake . -DCMAKE_POLICY_VERSION_MINIMUM=3.5
    make
}

do_compile() {
    # Build main application
    cd ${S}/build
    cmake -G 'Ninja' .. -DCMAKE_POLICY_VERSION_MINIMUM=3.5 -DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain.cmake -Wno-dev -DLV_CONF_BUILD_DISABLE_EXAMPLES=1 -DLV_CONF_BUILD_DISABLE_DEMOS=1
    ninja
}

do_install() {
    install -d ${D}${IMX_WIFI_SELECTOR_DIR}

    # Install main application from cmake build and rename it
    if [ -f "${S}/build/gui_guider" ]; then
        install -m 0755 ${S}/build/gui_guider ${D}${IMX_WIFI_SELECTOR_DIR}/wifi-selector
    fi

    # Install wificonnect.sh script
    if [ -f "${S}/scripts/wificonnect.sh" ]; then
        install -m 0755 ${S}/scripts/wificonnect.sh ${D}${IMX_WIFI_SELECTOR_DIR}/wificonnect.sh
    fi
}

FILES:${PN} += "${IMX_WIFI_SELECTOR_DIR}"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"