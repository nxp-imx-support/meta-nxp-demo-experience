DESCRIPTION = "NXP Demo Experience Voice App"
SECTION = "multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=63a38e9f392d8813d6f1f4d0d6fbe657"

inherit autotools pkgconfig

DEPENDS += "alsa-lib nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "MM_04.08.01_2308_L6.1.y"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "lf-6.1.36_2.1.0"

SRC_URI = "\
	${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
	${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-smart-kitchen"

SRC_URI:append:imx93-11x11-lpddr4x-evk = " file://0001-changed-BUILD_ARCH-CortexA55.patch "

SRCREV_FORMAT = "voice_model"

SRCREV_voice = "f43c722686009a6553a3189ba7b0449b1f66341d"
SRCREV_model = "ab9896ec4310b82b0fa402863a2d8b39f4f8fe7c"

S = "${WORKDIR}/git"
MODEL_DIR = "${WORKDIR}/demo-experience-smart-kitchen"

EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=/usr/lib/"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"

do_compile () {
	cp ${MODEL_DIR}/VIT_Model_en.h ${WORKDIR}/git/vit/i.MX9X_A55/Lib/VIT_Model_en.h
	cp ${MODEL_DIR}/VIT_Model_en.h ${WORKDIR}/git/vit/i.MX8M_A53/Lib/VIT_Model_en.h
	cd ${WORKDIR}/git
	make
}

do_install() {
    install -d ${D}/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen
    install -m 0755 ${WORKDIR}/git/release/voice_ui_app ${D}/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/home/root/.nxp-demo-experience/scripts/multimedia/smart-kitchen/"
INSANE_SKIP:${PN} += "dev-so"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
