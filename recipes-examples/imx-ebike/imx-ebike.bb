SUMMARY = "E-Bike VIT demo"
DESCRIPTION = "Recipe of E-Bike VIT demo application"
SECTION = "Multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1053d8bb787ee53eb7a075420a4a616e"

NXP_EBIKE_VIT_SRC ?= "git://github.com/nxp-imx-support/imx-ebike-vit.git;protocol=https"
SRCBRANCH = "master"
DEMODIR = "${GPNT_APPS_FOLDER}/scripts/multimedia/ebike-vit"

SRC_URI = "${NXP_EBIKE_VIT_SRC};branch=${SRCBRANCH};name=ebike \
           git://github.com/lvgl/lvgl.git;protocol=https;branch=release/v8.3;destsuffix=git/lvgl;name=lvgl \
           git://github.com/lvgl/lv_drivers.git;protocol=https;branch=release/v8.3;destsuffix=git/lv_drivers;name=drivers \
           file://0001-Include-unistd-h-header.patch"

SRCREV_ebike = "721652143fd4d6fbc43c49617031b263e762c2d4"
SRCREV_lvgl = "4d96c27ce35dd2ea6b34926f24a647e7ea7c4b0c"
SRCREV_drivers = "d52dc4f6b9b78cebd1183fb7fe5c0c3969cc47a2"
SRCREV_FORMAT = "ebike_lvgl_drivers"

S = "${WORKDIR}/git"

DEMOS ?= ""

DEPENDS = "wayland libxkbcommon libxdg-shell wayland-protocols xdg-utils"

RDEPENDS:${PN}+= " bash voiceui-ebike python3-posix-ipc libxdg-shell wayland-protocols xdg-utils"

do_configure:prepend() {
	cp -r  ${S}/wayland-client/*  ${S}/lv_drivers/wayland/
}

do_install() {
    install -d -m 755 ${D}/opt/gopoint-apps/scripts/multimedia/ebike-vit
    cp -r ${S}/ebike-vit-deploy/* ${D}/opt/gopoint-apps/scripts/multimedia/ebike-vit
}

FILES:${PN} += "/opt/gopoint-apps/scripts/multimedia/ebike-vit"

TARGET_CC_ARCH += "${LDFLAGS}"
