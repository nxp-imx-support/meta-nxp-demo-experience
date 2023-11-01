DESCRIPTION = "NXP Demo Multimedia Player Voice App"
SECTION = "multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=db4762b09b6bda63da103963e6e081de"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "voice_2.0"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "next"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
NXP_IMX_VOICEPLAYER_SRC ?= "${NXP_BTPLAYER_SRC}"
SRCBRANCH_player = "master"

BTDEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

SRC_URI = "\
        ${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
        ${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-voice-demo-bt-player \
        ${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH_player};name=player;subpath=voiceAction \
        file://0001-Change-Recipe-Target-Sysroot-path.patch \
        "

SRC_URI:append:imx93-11x11-lpddr4x-evk = " file://0001-changed-BUILD_ARCH-CortexA55.patch "

SRCREV_FORMAT = "voice_model_player"
SRCREV_voice = "${AUTOREV}"
SRCREV_model = "${AUTOREV}"
SRCREV_player = "${AUTOREV}"

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

EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=${libdir}"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"

#do_patch:mx93-generic-bsp() {
#
#    mv ${WORKDIR}/0001-changed-BUILD_ARCH-CortexA55.patch ${WORKDIR}/git
#    cd ${WORKDIR}/git && git apply 0001-changed-BUILD_ARCH-CortexA55.patch
#    mv ${WORKDIR}/0001-Change-Recipe-Target-Sysroot-path.patch ${WORKDIR}/voiceAction
#    cd ${WORKDIR}/voiceAction && git apply 0001-Change-Recipe-Target-Sysroot-path.patch
#}

do_patch() {
    mv ${WORKDIR}/0001-Change-Recipe-Target-Sysroot-path.patch ${WORKDIR}/voiceAction
    cd ${WORKDIR}/voiceAction && git apply 0001-Change-Recipe-Target-Sysroot-path.patch
}

do_compile() {
    cp ${WORKDIR}/demo-experience-voice-demo-bt-player/VIT_Model_en.h ${WORKDIR}/git/vit/platforms/iMX8M_CortexA53/lib/VIT_Model_en.h
    cp ${WORKDIR}/demo-experience-voice-demo-bt-player/VIT_Model_en.h ${WORKDIR}/git/vit/platforms/iMX9_CortexA55/lib/VIT_Model_en.h
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
