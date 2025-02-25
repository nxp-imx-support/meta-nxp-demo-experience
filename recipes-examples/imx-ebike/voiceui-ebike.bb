DESCRIPTION = "Voice App for E-Bike VIT"
SECTION = "Multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=10c0fda810c63b052409b15a5445671a"

inherit autotools pkgconfig

DEPENDS += "alsa-lib nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "MM_04.09.00_2405_L6.6.y"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "lf-6.12.3_1.0.0"

SRC_URI = "\
	${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
	${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-ebike-vit"

SRCREV_FORMAT = "voice_model"

SRCREV_voice = "cc51bc7475c0134fcb006ba28a16b2dcd418cf3a"
SRCREV_model = "cce123ab86c3861d46b8f29a88866bf9bf771f71"

S = "${WORKDIR}/git"
MODEL_DIR = "${UNPACKDIR}/demo-experience-ebike-vit"

EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=${libdir}"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"

do_compile () {
	cp ${MODEL_DIR}/VIT_Model_en.h ${WORKDIR}/git/vit/platforms/iMX8M_CortexA53/lib/VIT_Model_en.h
	cp ${MODEL_DIR}/VIT_Model_en.h ${WORKDIR}/git/vit/platforms/iMX9_CortexA55/lib/VIT_Model_en.h
	cd ${WORKDIR}/git
	oe_runmake
}

do_install() {
    install -d ${D}/opt/gopoint-apps/scripts/multimedia/ebike-vit
    install -m 0755 ${WORKDIR}/git/release/voice_ui_app ${D}/opt/gopoint-apps/scripts/multimedia/ebike-vit
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "${GPNT_APPS_FOLDER}/scripts/multimedia/ebike-vit/"
INSANE_SKIP:${PN} += "dev-so"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
