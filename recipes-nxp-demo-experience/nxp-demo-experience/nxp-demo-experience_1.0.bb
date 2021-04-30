# Copyright 2021 NXP

SUMMARY = "NXP Demo Experience"
DESCRIPTION = "Launcher for NXP Demo Experience"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

S = "${WORKDIR}/git"

SRCBRANCH = "imx_5.10.y"
NXP_DEMO_SRC ?= "git://source.codeaurora.org/external/imxsupport/nxp-demo-experience;protocol=https"
NXP_DEMO_LIST_SRC ?= "git://source.codeaurora.org/external/imxsupport/nxp-demo-experience-demos-list;protocol=https"

SRC_URI = " \
    ${NXP_DEMO_SRC};branch=${SRCBRANCH};name=nxp-demo-experience \
    ${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};destsuffix=demos;name=demos "

SRCREV_FORMAT = "nxp-demo-experience_demos"
SRCREV_nxp-demo-experience = "${AUTOREV}"
SRCREV_demos = "${AUTOREV}"

inherit qmake5

DEPENDS += " packagegroup-qt5-imx qtquickcontrols2 qtconnectivity qtgraphicaleffects qtsvg"
RDEPENDS_${PN} += " weston bash qtgraphicaleffects-qmlplugins qtquickcontrols-qmlplugins qtsvg-plugins"

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience
    cp -r ${WORKDIR}/demos/* ${D}/home/root/.nxp-demo-experience

    install -d -m 755 ${D}${bindir}
    install ${WORKDIR}/build/demoexperience ${D}${bindir}
}

FILES_${PN} += "${bindir}* /home/root/.nxp-demo-experience/* "
