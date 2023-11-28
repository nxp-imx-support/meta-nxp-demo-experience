SUMMARY = "GoPoint for i.MX Application Processors"
DESCRIPTION = "Launcher for GoPoint for i.MX Application Processors"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f16dd419c71c562a26de3144de8f8ab8"

S = "${WORKDIR}/git"

SRCBRANCH = "lf-6.1.55_2.2.0"

NXP_DEMO_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience.git;protocol=https"
NXP_DEMO_LIST_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience-demos-list.git;protocol=https"


SRC_URI = " \
    ${NXP_DEMO_SRC};branch=${SRCBRANCH};name=nxp-demo-experience \
    ${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};destsuffix=demos;name=demos "

SRCREV_FORMAT = "nxp-demo-experience_demos"

SRCREV_nxp-demo-experience = "3e00acde71d0fa045ff7e015bdab4dcb1db49c74"
SRCREV_demos = "976175ce23d684f65f6c738a9d32f002cd13f402"

PV = "4.7+git${SRCPV}"

inherit qt6-qmake

DEMOS ?= ""
DEMOS:mx8mp-nxp-bsp = " demo-experience-voice-demo demo-experience-demo-btplayer demo-experience-smart-kitchen"
DEMOS:mx8mm-nxp-bsp = " demo-experience-voice-demo demo-experience-demo-btplayer demo-experience-smart-kitchen"
DEMOS:mx93-nxp-bsp = " demo-experience-demo-btplayer demo-experience-smart-kitchen"

DEPENDS += " packagegroup-qt6-imx qtconnectivity qtsvg"
RDEPENDS:${PN} += " weston bash qtsvg-plugins qt5compat ${DEMOS} python3-packaging python3-paramiko iproute2 iproute2-tc python3-matplotlib"

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience
    cp -r ${WORKDIR}/demos/* ${D}/home/root/.nxp-demo-experience

    install -d -m 755 ${D}${bindir}
    install ${WORKDIR}/build/demoexperience ${D}${bindir}

    ln -sfr ${D}${bindir}/demoexperience ${D}${bindir}/gopoint
}

FILES:${PN} += "${bindir}* /home/root/.nxp-demo-experience/* "
