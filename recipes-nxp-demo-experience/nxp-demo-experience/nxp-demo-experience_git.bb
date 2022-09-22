# Copyright 2020-2022 NXP

SUMMARY = "NXP Demo Experience"
DESCRIPTION = "Launcher for NXP Demo Experience"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

S = "${WORKDIR}/git"

SRCBRANCH = "lf-5.15.52_2.1.0"

NXP_DEMO_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience.git;protocol=https"
NXP_DEMO_LIST_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience-demos-list.git;protocol=https"

SRC_URI = " \
    ${NXP_DEMO_SRC};branch=${SRCBRANCH};name=nxp-demo-experience \
    ${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};destsuffix=demos;name=demos "

SRCREV_FORMAT = "nxp-demo-experience_demos"

SRCREV_nxp-demo-experience = "${AUTOREV}"
SRCREV_demos = "${AUTOREV}"

PV = "4.2+git${SRCPV}"

inherit qt6-qmake

DEMOS ?= ""
DEMOS:mx8mp-nxp-bsp = " demo-experience-voice-demo"
DEMOS:mx8mm-nxp-bsp = " demo-experience-voice-demo"

DEPENDS += " packagegroup-qt6-imx qtconnectivity qtsvg"
RDEPENDS:${PN} += " weston bash qtsvg-plugins qt5compat ${DEMOS}"

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience
    cp -r ${WORKDIR}/demos/* ${D}/home/root/.nxp-demo-experience

    install -d -m 755 ${D}${bindir}
    install ${WORKDIR}/build/demoexperience ${D}${bindir}
}

FILES:${PN} += "${bindir}* /home/root/.nxp-demo-experience/* "
