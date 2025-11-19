DESCRIPTION = "Voice App for E-Bike VIT"
SECTION = "Multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ca53281cc0caa7e320d4945a896fb837"

inherit pkgconfig

DEPENDS += "alsa-lib nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "MM_04.10.02_2510_L6.12.49"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "lf-6.12.49_2.2.0"

SRC_URI = "\
	${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
	${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-ebike-vit"

SRCREV_FORMAT = "voice_model"

SRCREV_voice = "f2ff8703685b511371c3475f8c52c73dc1d21f32"
SRCREV_model = "eb433849ba76bbe3100fb6ffbd48183468ac53e5"

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
