SUMARY = "Message queue command sender"
DESCRIPTION = "MsgQ app used by i.MX Voice Player to send MAC address of current mobile device connected through bluetooth"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=02214c96bef56300b0c1f4c6887d3114"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
NXP_IMX_VOICEPLAYER_SRC ?= "${NXP_BTPLAYER_SRC}"
SRCBRANCH = "master"
SRCREV = "f3cff5ec52c456ef993b2a88200a424244d8ae5f"

IMX_VOICE_PLAYER_DIR = "/home/root/.nxp-demo-experience/scripts/multimedia/imx-voiceplayer"

SRC_URI = "${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH} \
          "

S = "${WORKDIR}/git/msgq"

inherit pkgconfig cmake

do_install() {
    install -d -m 755 ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/build/MsgQ ${D}${IMX_VOICE_PLAYER_DIR}
}

FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/MsgQ"
