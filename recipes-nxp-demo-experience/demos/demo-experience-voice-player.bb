SUMARY = "i.MX Voice Player for i.MX"
DESCRIPTION = "Recipe for i.MX Voice Player application"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=02214c96bef56300b0c1f4c6887d3114"
IMX_VOICE_PLAYER_DIR = "/home/root/.nxp-demo-experience/scripts/multimedia/imx-voiceplayer"

NXP_IMX_VOICEPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
SRCBRANCH = "master"
SRCREV = "f3cff5ec52c456ef993b2a88200a424244d8ae5f"

SRC_URI = "${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH} \
          "
S = "${WORKDIR}/git/app"

DEMOS ?= ""
DEPENDS += "  packagegroup-qt6-imx qtconnectivity qtsvg"
RDEPENDS:${PN}+= " demo-experience-msgq-player demo-experience-voiceui-player bash"

inherit qt6-qmake

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience/scripts/multimedia/imx-voiceplayer
    install ${WORKDIR}/build/VoicePlayer ${D}${IMX_VOICE_PLAYER_DIR}
    
    install ${WORKDIR}/git/scripts/connect.sh ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/Enable_VIT_Auto_Start.sh ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/Enable_VoiceSeeker.sh ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/volume.sh ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/init.sh ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/Restore_AFEConfig.sh ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/Config.ini ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/bt-init.sh ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/git/scripts/stop.sh ${D}${IMX_VOICE_PLAYER_DIR}
}

FILES:${PN} += "${IMX_VOICE_PLAYER_DIR} "

INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN}-dev += "ldflags"

