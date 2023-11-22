SUMARY = "Message queue command sender"
DESCRIPTION = "The MsgQ app it's used by Btplayer app to send the MAC address of the current mobile device connected through bt"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=02214c96bef56300b0c1f4c6887d3114"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
NXP_IMX_VOICEPLAYER_SRC ?= "${NXP_BTPLAYER_SRC}"
SRCBRANCH = "master"
SRCREV = "${AUTOREV}"

BTDEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

SRC_URI = "${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH} \
          "

S = "${WORKDIR}/git/msgq"

inherit pkgconfig cmake

do_install() {
    install -d -m 755 ${D}${BTDEMODIR}
    install ${WORKDIR}/build/MsgQ ${D}${BTDEMODIR}
}

FILES:${PN} += "${BTDEMODIR}/MsgQ"
