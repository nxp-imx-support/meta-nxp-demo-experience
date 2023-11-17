SUMMARY = "GoPoint for i.MX Application Processors"
DESCRIPTION = "Launcher for GoPoint for i.MX Application Processors"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"

S = "${WORKDIR}/git"

SRCBRANCH = "lf-6.1.52_2.2.0"

NXP_DEMO_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience.git;protocol=https"
NXP_DEMO_LIST_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience-demos-list.git;protocol=https"


SRC_URI = " \
    ${NXP_DEMO_SRC};branch=${SRCBRANCH};name=nxp-demo-experience \
    ${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};destsuffix=demos;name=demos "

SRCREV_FORMAT = "nxp-demo-experience_demos"

SRCREV_nxp-demo-experience = "ab2467af394f743e6bfc9081be66f39401849e4f"
SRCREV_demos = "5a2d51f0191d89927f28d09356c9d3af54348a3c"

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
