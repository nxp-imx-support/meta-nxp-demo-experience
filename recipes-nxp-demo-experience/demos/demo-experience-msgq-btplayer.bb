# Copyright 2020-2023 NXP

SUMARY = "Message queue command sender"
DESCRIPTION = "The MsgQ app it's used by Btplayer app to send the MAC address of the current mobile device connected through bt"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"
SRCBRANCH = "master"
BTDEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

SRC_URI = "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https;branch=${SRCBRANCH} \
          "

SRCREV = "e6178bc98212713cef4cdf34072c0dca203065fd"

S = "${WORKDIR}/git/msgq"

inherit pkgconfig cmake

do_install() {
    install -d -m 755 ${D}${BTDEMODIR}
    install ${WORKDIR}/build/MsgQ ${D}${BTDEMODIR}
}

FILES:${PN} += "${BTDEMODIR}/MsgQ"
#FILES:${PN} += "${bindir}* /home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo/* "
