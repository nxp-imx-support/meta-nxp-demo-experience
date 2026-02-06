DESCRIPTION = "GoPoint Voice App"
SECTION = "Multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ca53281cc0caa7e320d4945a896fb837"

inherit pkgconfig

DEPENDS += "alsa-lib nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker"

PV = "1.0+${SRCPV}"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "MM_04.10.03_2512_L6.18.2"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "lf-6.18.2_1.0.0"

SRC_URI = "\
    ${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
    ${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-voice-demo \
    "

SRCREV_FORMAT = "voice_model"

SRCREV_voice = "94639a8ea4a65a218f60b900269a7dc4bf97fefb"
SRCREV_model = "0050fde71c50b4077e3dc59f9084c94468ed4996"


EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=${libdir}"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"

do_compile () {
    cp ${UNPACKDIR}/demo-experience-voice-demo/VIT_Model_en.h ${UNPACKDIR}/${BP}/vit/platforms/iMX8M_CortexA53/lib/VIT_Model_en.h
    cd ${UNPACKDIR}/${BP}
    oe_runmake VOICE_UI_APP
}

do_install() {
    install -d ${D}${GPNT_APPS_FOLDER}/bin
    install -m 0755 ${UNPACKDIR}/${BP}/release/voice_ui_app ${D}${GPNT_APPS_FOLDER}/bin
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "${GPNT_APPS_FOLDER}/bin/voice_ui_app"
INSANE_SKIP:${PN} += "dev-so"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
