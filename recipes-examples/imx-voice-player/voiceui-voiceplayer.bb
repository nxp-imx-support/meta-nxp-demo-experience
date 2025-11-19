DESCRIPTION = "Voice App for i.MX Voice Player"
SECTION = "Multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ca53281cc0caa7e320d4945a896fb837"

NXPAFE_VOICESEEKER_SRC ?= "git://github.com/nxp-imx/imx-voiceui.git;protocol=https"
SRCBRANCH_voice = "MM_04.10.02_2510_L6.12.49"

NXP_DEMO_ASSET_SRC ?= "git://github.com/NXP/nxp-demo-experience-assets.git;protocol=https"
SRCBRANCH_model = "lf-6.12.49_2.2.0"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
NXP_IMX_VOICEPLAYER_SRC ?= "${NXP_BTPLAYER_SRC}"
SRCBRANCH_player = "master"

IMX_VOICE_PLAYER_DIR = "${GPNT_APPS_FOLDER}/scripts/multimedia/imx-voiceplayer"

SRC_URI = "\
        ${NXPAFE_VOICESEEKER_SRC};branch=${SRCBRANCH_voice};name=voice \
        ${NXP_DEMO_ASSET_SRC};branch=${SRCBRANCH_model};name=model;subpath=build/demo-experience-voice-player \
        ${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH_player};name=player;subpath=voiceAction \
        file://0001-Makefile-Fix-undefined-dbus-references.patch;patchdir=${UNPACKDIR}/voiceAction \
        file://0002-Makefile-Handle-multilib.patch;patchdir=${UNPACKDIR}/voiceAction \
        "

SRCREV_FORMAT = "voice_model_player"
SRCREV_voice = "f2ff8703685b511371c3475f8c52c73dc1d21f32"
SRCREV_model = "eb433849ba76bbe3100fb6ffbd48183468ac53e5"
SRCREV_player = "ab1304afa7fa4ec4f839bbe0b9c06dadb2a21d25"

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
EXTRA_OEMAKE:append = " OECORE_TARGET_SYSROOT=${STAGING_DIR_HOST}"

do_compile() {
    cp ${UNPACKDIR}/demo-experience-voice-player/VIT_Model_en.h ${S}/vit/platforms/iMX8M_CortexA53/lib/VIT_Model_en.h
    cp ${UNPACKDIR}/demo-experience-voice-player/VIT_Model_en.h ${S}/vit/platforms/iMX9_CortexA55/lib/VIT_Model_en.h
    cd ${S}
    oe_runmake
    cd ${UNPACKDIR}/voiceAction
    oe_runmake
}

do_install() {
        install -d -m 0755 ${D}${IMX_VOICE_PLAYER_DIR}
        install -d -m 0755 ${D}${IMX_VOICE_PLAYER_DIR}/i.MX8M_A53
        install -d -m 0755 ${D}${IMX_VOICE_PLAYER_DIR}/i.MX9X_A55
        install -m 0755 ${S}/release/voice_ui_app ${D}${IMX_VOICE_PLAYER_DIR}/i.MX8M_A53
        install -m 0755 ${S}/release/voice_ui_app ${D}${IMX_VOICE_PLAYER_DIR}/i.MX9X_A55
        install -m 0755 ${S}/release/libvoiceseekerlight.so.2.0 ${D}${IMX_VOICE_PLAYER_DIR}
        install -m 0755 ${UNPACKDIR}/voiceAction/build/btp ${D}${IMX_VOICE_PLAYER_DIR}
        install -m 0755 ${UNPACKDIR}/voiceAction/bridgeVoiceUI/WakeWordNotify ${D}${IMX_VOICE_PLAYER_DIR}
        install -m 0755 ${UNPACKDIR}/voiceAction/bridgeVoiceUI/WWCommandNotify ${D}${IMX_VOICE_PLAYER_DIR}
}

FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/i.MX8M_A53/voice_ui_app"
FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/i.MX9X_A55/voice_ui_app"
FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/HeyNXP_1_params.bin"
FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/HeyNXP_en-US_1.bin"
FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/libvoiceseekerlight.so.2.0"
FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/btp"
FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/WakeWordNotify"
FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/WWCommandNotify"

INSANE_SKIP_${PN} += "ldflags"
TARGET_CC_ARCH += "${LDFLAGS}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
