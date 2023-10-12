SUMARY = "Smart Kitchen demo"
DESCRIPTION = "Recipe of Smart Kitchen demo application"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

NXP_SMART_KITCHEN_SRC ?= "gitsm://github.com/nxp-imx-support/smart-kitchen.git"
SRCBRANCH = "master"
DEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen"


SRC_URI = "${NXP_SMART_KITCHEN_SRC};protocol=ssh;branch=${SRCBRANCH} \
			file://0001-Added-custom_tick_get-function.patch"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEMOS ?= ""

DEPENDS = "wayland libxkbcommon libxdg-shell wayland-protocols xdg-utils"

RDEPENDS:${PN}+= " bash demo-experience-imx-voiceui-smart-kitchen python3-posix-ipc libxdg-shell wayland-protocols xdg-utils"

do_patch() {
	mv ${WORKDIR}/0001-Added-custom_tick_get-function.patch ${WORKDIR}/git/lvgl
	cd ${WORKDIR}/git/lvgl/ && git am 0001-Added-custom_tick_get-function.patch
	cd ${WORKDIR}/git/
	cp -r wayland-client/* lv_drivers/wayland/
}

do_compile() {
	cd ${S}
	make
}

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen
    cp -r ${S}/smart-kitchen-deploy/* ${D}/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen
}

FILES:${PN} += "/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen"

TARGET_CC_ARCH += "${LDFLAGS}"
