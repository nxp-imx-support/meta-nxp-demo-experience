DESCRIPTION = "NXP Demo Experience Voice App"
SECTION = "multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=db4762b09b6bda63da103963e6e081de"

inherit autotools pkgconfig

DEPENDS += "alsa-lib nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "voice_2.0"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "next"

SRC_URI = "\
	${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
	${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-smart-kitchen"

SRC_URI:append:imx93-11x11-lpddr4x-evk = " file://0001-changed-BUILD_ARCH-CortexA55.patch "

SRCREV_FORMAT = "voice_model"

SRCREV_voice = "${AUTOREV}"
SRCREV_model = "${AUTOREV}"

S = "${WORKDIR}/git"
MODEL_DIR = "${WORKDIR}/demo-experience-smart-kitchen"

EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=/usr/lib/"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"

do_compile () {
	cp ${MODEL_DIR}/VIT_Model_en.h ${WORKDIR}/git/vit/platforms/iMX8M_CortexA53/lib/VIT_Model_en.h
    cp ${MODEL_DIR}/VIT_Model_en.h ${WORKDIR}/git/vit/platforms/iMX9_CortexA55/lib/VIT_Model_en.h
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
