# Copyright 2021-2022 NXP

DESCRIPTION = "NXP Demo Experience Voice App"
SECTION = "multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=e4098ac4459cb81b07d3f0c22b3e8370"

inherit autotools pkgconfig

DEPENDS += "alsa-lib nxp-afe"

PV = "1.0+${SRCPV}"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/NXP/imx-voiceUI.git;protocol=https"
SRCBRANCH_voice = "voice_2.0"

MODEL_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "imx_5.15.y"

SRC_URI = "\
    ${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
    ${MODEL_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-voice-demo"

SRCREV_FORMAT = "voice_model"

SRCREV_voice = "fb63d3ac7ea7e2b836bc74cd97e269ef1f910b34"
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
