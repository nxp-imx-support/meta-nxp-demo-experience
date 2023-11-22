SUMARY = "BTPlayer demo"
DESCRIPTION = "Recipe of btplayer demo application"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=02214c96bef56300b0c1f4c6887d3114"
BTDEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

NXP_BTPLAYER_SRC ?= "git://github.com/nxp-imx-support/imx-voiceplayer.git;protocol=https"
NXP_IMX_VOICEPLAYER_SRC ?= "${NXP_BTPLAYER_SRC}"
SRCBRANCH = "master"
SRCREV = "c140ffae7b97dc90ac49ba1fcffd91f9a1e983fd"

SRC_URI = "${NXP_IMX_VOICEPLAYER_SRC};branch=${SRCBRANCH} \
          "
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
    install ${WORKDIR}/git/scripts/init.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/scripts/Restore_AFEConfig.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/scripts/Config.ini ${D}${BTDEMODIR}
    install ${WORKDIR}/git/scripts/bt-init.sh ${D}${BTDEMODIR}
    install ${WORKDIR}/git/scripts/stop.sh ${D}${BTDEMODIR}
}

FILES:${PN} += "${BTDEMODIR} "

INSANE_SKIP_${PN} += "ldflags"
INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN}-dev += "ldflags"

