SUMARY = "Message queue command sender"
DESCRIPTION = "MsgQ app used by i.MX Voice Player to send MAC address of current mobile device connected through bluetooth"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=50abc977283affbd6ec84a32b458cb61"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
NXP_IMX_VOICEPLAYER_SRC ?= "${NXP_BTPLAYER_SRC}"
SRCBRANCH = "master"
SRCREV = "ab1304afa7fa4ec4f839bbe0b9c06dadb2a21d25"

IMX_VOICE_PLAYER_DIR = "${GPNT_APPS_FOLDER}/scripts/multimedia/imx-voiceplayer"

SRC_URI = "${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH} \
          "

S = "${WORKDIR}/git/msgq"

inherit pkgconfig cmake

do_install() {
    install -d -m 755 ${D}${IMX_VOICE_PLAYER_DIR}
    install ${WORKDIR}/build/MsgQ ${D}${IMX_VOICE_PLAYER_DIR}
}

FILES:${PN} += "${IMX_VOICE_PLAYER_DIR}/MsgQ"
