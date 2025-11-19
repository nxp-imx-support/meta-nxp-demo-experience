SUMMARY = "Smart Kitchen demo"
DESCRIPTION = "Recipe of Smart Kitchen demo application"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=242ef8a3d67a79b1e67096c5e87de59d"

NXP_SMART_KITCHEN_SRC ?= "gitsm://github.com/nxp-imx-support/smart-kitchen.git;protocol=https"
SRCBRANCH = "master"
DEMODIR = "${GPNT_APPS_FOLDER}/scripts/multimedia/smart-kitchen"


SRC_URI = "${NXP_SMART_KITCHEN_SRC};branch=${SRCBRANCH} \
			file://0001-Added-custom_tick_get-function.patch \
      file://0001-Update-lv_anim_set_exec_cb-with-correct-function-typ.patch  \
      "


SRCREV = "1f42aceae2e79f4b5c7cd29c169cc3ebd1fce78a"

S = "${WORKDIR}/git"

DEMOS ?= ""

DEPENDS = "wayland libxkbcommon libxdg-shell wayland-protocols xdg-utils"

RDEPENDS:${PN}+= " bash voiceui-smart-kitchen python3-posix-ipc libxdg-shell wayland-protocols xdg-utils"

do_patch() {
	cp ${UNPACKDIR}/0001-Added-custom_tick_get-function.patch ${WORKDIR}/git/lvgl
	cp ${UNPACKDIR}/0001-Update-lv_anim_set_exec_cb-with-correct-function-typ.patch ${WORKDIR}/git/
	cd ${WORKDIR}/git/lvgl/ && git apply 0001-Added-custom_tick_get-function.patch
	cd ${WORKDIR}/git/
	cp -r wayland-client/* lv_drivers/wayland/
  git apply 0001-Update-lv_anim_set_exec_cb-with-correct-function-typ.patch
}

do_compile() {
	cd ${S}
	make
}

do_install() {
    install -d -m 755 ${D}${GPNT_APPS_FOLDER}/scripts/multimedia/smart-kitchen
    cp -r ${S}/smart-kitchen-deploy/* ${D}${GPNT_APPS_FOLDER}/scripts/multimedia/smart-kitchen
}

FILES:${PN} += "${GPNT_APPS_FOLDER}/scripts/multimedia/smart-kitchen"

TARGET_CC_ARCH += "${LDFLAGS}"
