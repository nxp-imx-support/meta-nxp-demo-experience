SUMMARY = "EdgeLock Enclave(ELE) security demo"
DESCRIPTION = "Recipe of ELE demo application"
SECTION = "Security"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a9b152a21821b0419330795aee9f6602"

NXP_ELE_DEMO_SRC ?= "gitsm://github.com/nxp-imx-support/imx-ele-demo.git;protocol=https"

SRCBRANCH = "main"
DEMODIR = "${GPNT_APPS_FOLDER}/scripts/security/ele"

SRC_URI = "${NXP_ELE_DEMO_SRC};branch=${SRCBRANCH}\
			file://0001-fix-wayland-busy-flush-and-add-wm_capabilities.patch"

SRCREV = "2134feeef0c7a89b02664c97b5083c6a47094b85"

S = "${WORKDIR}/git"

DEMOS ?= ""

DEPENDS = "openssl wayland libxkbcommon"
DEPENDS:append = " imx-secure-enclave"

RDEPENDS:${PN}+= "bash"

EXTRA_OEMAKE = "ELE_ROOT=${STAGING_DIR_HOST}"

do_patch() {
	mv ${UNPACKDIR}/0001-fix-wayland-busy-flush-and-add-wm_capabilities.patch ${WORKDIR}/git/lv_drivers
	cd ${WORKDIR}/git/lv_drivers && git apply 0001-fix-wayland-busy-flush-and-add-wm_capabilities.patch
	cd ${WORKDIR}/git/
	cp -rf protocols/ lv_drivers/wayland/
}

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    install -d -m 755 ${D}${GPNT_APPS_FOLDER}/scripts/security/ele
    cp -r ${S}/bin/eledemo ${D}${GPNT_APPS_FOLDER}/scripts/security/ele
    cp -r ${S}/misc/script/run.sh ${D}${GPNT_APPS_FOLDER}/scripts/security/ele
}

FILES:${PN} += "${GPNT_APPS_FOLDER}/scripts/security/ele"

TARGET_CC_ARCH += "${LDFLAGS}"
