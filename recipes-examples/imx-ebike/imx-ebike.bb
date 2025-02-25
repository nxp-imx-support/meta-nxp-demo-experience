SUMARY = "E-Bike VIT demo"
DESCRIPTION = "Recipe of E-Bike VIT demo application"
SECTION = "Multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=1053d8bb787ee53eb7a075420a4a616e"

NXP_EBIKE_VIT_SRC ?= "gitsm://github.com/nxp-imx-support/imx-ebike-vit.git;protocol=https;branch=${SRCBRANCH} \
			                  file://0001-Include-unistd-h-header.patch"
SRCBRANCH = "master"
DEMODIR = "${GPNT_APPS_FOLDER}/scripts/multimedia/ebike-vit"

SRC_URI = "${NXP_EBIKE_VIT_SRC}"

SRCREV = "6c5917c8afa70ed0ac832184f6b8e289cb740905"

S = "${WORKDIR}/git"

DEMOS ?= ""

DEPENDS = "wayland libxkbcommon libxdg-shell wayland-protocols xdg-utils"

RDEPENDS:${PN}+= " bash voiceui-ebike python3-posix-ipc libxdg-shell wayland-protocols xdg-utils"

do_patch() {
	cp ${UNPACKDIR}/0001-Include-unistd-h-header.patch ${WORKDIR}/git/
	cd ${WORKDIR}/git/
       git apply 0001-Include-unistd-h-header.patch
	cp -r wayland-client/* lv_drivers/wayland/
}

do_compile() {
	cd ${S}
	make
}

do_install() {
    install -d -m 755 ${D}/opt/gopoint-apps/scripts/multimedia/ebike-vit
    cp -r ${S}/ebike-vit-deploy/* ${D}/opt/gopoint-apps/scripts/multimedia/ebike-vit
}

FILES:${PN} += "/opt/gopoint-apps/scripts/multimedia/ebike-vit"

TARGET_CC_ARCH += "${LDFLAGS}"
