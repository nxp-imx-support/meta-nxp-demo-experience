# Copyright 2021-2022 NXP

DESCRIPTION = "NXP Demo Experience Voice App"
SECTION = "multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=${FSL_EULA_FILE_MD5SUM_LA_OPT_NXP_SOFTWARE_LICENSE_V38}"

inherit autotools pkgconfig

DEPENDS += "alsa-lib nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker"

PV = "1.0+${SRCPV}"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "MM_04.07.01_2208_L5.15.y"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "lf-5.15.52_2.1.0"

SRC_URI = "\
    ${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
    ${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-voice-demo"

SRCREV_FORMAT = "voice_model"

SRCREV_voice = "698a581d87467ad9588efd4a49ddd704774994e8"
SRCREV_model = "${AUTOREV}"

S = "${WORKDIR}/git"

EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=/usr/lib/"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"

do_compile () {
    mv ${WORKDIR}/demo-experience-voice-demo/VIT_Model_en.h ${WORKDIR}/git/vit/i.MX8M_A53/Lib/VIT_Model_en.h
    cd ${WORKDIR}/git
    oe_runmake VOICESPOT
}

do_install() {
    install -d ${D}/home/root/.nxp-demo-experience/bin
    install -m 0755 ${WORKDIR}/git/release/voice_ui_app ${D}/home/root/.nxp-demo-experience/bin
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/home/root/.nxp-demo-experience/bin/voice_ui_app"
INSANE_SKIP:${PN} += "dev-so"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
