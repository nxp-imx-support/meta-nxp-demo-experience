DESCRIPTION = "NXP Demo Multimedia Player Voice App"
SECTION = "multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=63a38e9f392d8813d6f1f4d0d6fbe657"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "MM_04.08.01_2308_L6.1.y"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "next"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
SRCBRANCH_player = "next"

BTDEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

SRC_URI = "\
        ${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
        ${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-voice-demo-bt-player \
        ${NXP_BTPLAYER_SRC};branch=${SRCBRANCH_player};name=player;subpath=voiceAction \
        file://0001-Change-Recipe-Target-Sysroot-path.patch \
        "
SRC_URI:append:imx93-11x11-lpddr4x-evk = " file://0001-changed-BUILD_ARCH-CortexA55.patch "

SRCREV_FORMAT = "voice_model_player"
SRCREV_voice = "f43c722686009a6553a3189ba7b0449b1f66341d"
SRCREV_model = "${AUTOREV}"
SRCREV_player = "378da8c0d32384038e2bc20cac7cba5c391dec9d"

S = "${WORKDIR}/git"

DEPENDS += " \
    portaudio-v19 \
    dbus \
    dbus-glib \
    dbus-glib-native \
    glib-2.0 \
    alsa-lib \
    nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker bash"

EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=/usr/lib/"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"

do_patch() {
    mv ${WORKDIR}/0001-Change-Recipe-Target-Sysroot-path.patch ${WORKDIR}/voiceAction
   cd ${WORKDIR}/voiceAction && git apply 0001-Change-Recipe-Target-Sysroot-path.patch
}

do_compile() {
    cp ${WORKDIR}/demo-experience-voice-demo-bt-player/VIT_Model_en.h ${WORKDIR}/git/vit/i.MX8M_A53/Lib/VIT_Model_en.h
    cp ${WORKDIR}/demo-experience-voice-demo-bt-player/VIT_Model_en.h ${WORKDIR}/git/vit/i.MX9X_A55/Lib/VIT_Model_en.h
    cd ${WORKDIR}/git
    make
    cd ${WORKDIR}/voiceAction
    make
}

do_install() {
        install -d -m 0755 ${D}${BTDEMODIR}
        install -d -m 0755 ${D}${BTDEMODIR}/i.MX8M_A53
        install -d -m 0755 ${D}${BTDEMODIR}/i.MX9X_A55
        install -m 0755 ${WORKDIR}/git/release/voice_ui_app ${D}${BTDEMODIR}/i.MX8M_A53
        install -m 0755 ${WORKDIR}/git/release/voice_ui_app ${D}${BTDEMODIR}/i.MX9X_A55
        install -m 0755 ${WORKDIR}/git/release/HeyNXP_1_params.bin ${D}${BTDEMODIR}
        install -m 0755 ${WORKDIR}/git/release/HeyNXP_en-US_1.bin ${D}${BTDEMODIR}
        install -m 0755 ${WORKDIR}/git/release/libvoiceseekerlight.so.2.0 ${D}${BTDEMODIR}
        install -m 0755 ${WORKDIR}/voiceAction/build/btp ${D}${BTDEMODIR}
        install -m 0755 ${WORKDIR}/voiceAction/bridgeVoiceUI/WakeWordNotify ${D}${BTDEMODIR}
        install -m 0755 ${WORKDIR}/voiceAction/bridgeVoiceUI/WWCommandNotify ${D}${BTDEMODIR}
}

FILES:${PN} += "${BTDEMODIR}/i.MX8M_A53/voice_ui_app"
FILES:${PN} += "${BTDEMODIR}/i.MX9X_A55/voice_ui_app"
FILES:${PN} += "${BTDEMODIR}/HeyNXP_1_params.bin"
FILES:${PN} += "${BTDEMODIR}/HeyNXP_en-US_1.bin"
FILES:${PN} += "${BTDEMODIR}/libvoiceseekerlight.so.2.0"
FILES:${PN} += "${BTDEMODIR}/btp"
FILES:${PN} += "${BTDEMODIR}/WakeWordNotify"
FILES:${PN} += "${BTDEMODIR}/WWCommandNotify"

INSANE_SKIP_${PN} += "ldflags"
TARGET_CC_ARCH += "${LDFLAGS}"
