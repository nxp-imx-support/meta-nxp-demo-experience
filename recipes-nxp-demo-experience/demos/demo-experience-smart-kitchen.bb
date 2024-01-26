SUMARY = "Smart Kitchen demo"
DESCRIPTION = "Recipe of Smart Kitchen demo application"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=bfc86b98f9b03945e2b6cf6aa6a2030b"

NXP_SMART_KITCHEN_SRC ?= "gitsm://github.com/nxp-imx-support/smart-kitchen.git;protocol=https"
SRCBRANCH = "master"
DEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen"


SRC_URI = "${NXP_SMART_KITCHEN_SRC};branch=${SRCBRANCH} \
			file://0001-Added-custom_tick_get-function.patch"

SRCREV = "c60a2f981f6fb65f8fa429b69fb20606005eb2e5"

S = "${WORKDIR}/git"

DEMOS ?= ""

DEPENDS = "wayland libxkbcommon libxdg-shell wayland-protocols xdg-utils"

RDEPENDS:${PN}+= " bash demo-experience-imx-voiceui-smart-kitchen python3-posix-ipc libxdg-shell wayland-protocols xdg-utils"

do_patch() {
	cp ${WORKDIR}/0001-Added-custom_tick_get-function.patch ${WORKDIR}/git/lvgl
	cd ${WORKDIR}/git/lvgl/ && git apply 0001-Added-custom_tick_get-function.patch
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
