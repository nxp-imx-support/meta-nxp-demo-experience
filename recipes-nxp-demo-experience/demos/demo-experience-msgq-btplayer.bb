SUMARY = "Message queue command sender"
DESCRIPTION = "MsgQ app used by i.MX Voice Player to send MAC address of current mobile device connected through bluetooth"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=02214c96bef56300b0c1f4c6887d3114"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
NXP_IMX_VOICEPLAYER_SRC ?= "${NXP_BTPLAYER_SRC}"
SRCBRANCH = "master"
SRCREV = "3b494ff80b48b83a5e130063b83608490c0d77e8"

IMX_VOICE_PLAYER_DIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

SRC_URI = "${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH} \
          "

S = "${WORKDIR}/git/msgq"

inherit pkgconfig cmake

do_install() {
    install -d -m 755 ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/build/MsgQ ${D}${IMX_VOICE_PLAYER_DIR}
}

FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/MsgQ"
