# Copyright 2020-2023 NXP

SUMARY = "BTPlayer demo"
DESCRIPTION = "Recipe of btplayer demo application"
SECTION = "Multimedia"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"
SRCBRANCH = "master"
BTDEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

SRC_URI = "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https;branch=${SRCBRANCH} \
          file://0001-Use-QML-loader-for-splashScreen-and-main-view.patch \
          file://0001-Add-Multimedia-Player-title.patch \
          "
SRCREV = "e6178bc98212713cef4cdf34072c0dca203065fd"

S = "${WORKDIR}/git/app"

DEMOS ?= ""
DEPENDS += "  packagegroup-qt6-imx qtconnectivity qtsvg"
RDEPENDS:${PN}+= " demo-experience-msgq-btplayer demo-experience-vit-btplayer bash"

inherit qt6-qmake

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo
    install ${WORKDIR}/build/Btplayer ${D}${BTDEMODIR}
    
    install ${WORKDIR}/git/scripts/connect.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/scripts/Enable_VIT_Auto_Start.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/scripts/Enable_VoiceSeeker.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/scripts/volume.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/app/init.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/app/Restore_AFEConfig.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/app/Config.ini ${D}${BTDEMODIR}
    install ${WORKDIR}/git/app/bt-init.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/app/stop.sh ${D}${BTDEMODIR}
}

FILES:${PN} += "${BTDEMODIR} "

INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN}-dev += "ldflags"

