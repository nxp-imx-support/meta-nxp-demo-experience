# Copyright 2020 NXP

SUMMARY = "NXP Demo Experience"
DESCRIPTION = "Launcher for NXP Demo Experience"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCBRANCH = "warrior-4.19.35-1.1.0"
SRC_URI = "git://source.codeaurora.org/external/imxsupport/nxp-demo-experience;branch=${SRCBRANCH}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit qmake5

DEPENDS += " qtbase qtquickcontrols2 qtconnectivity qtgraphicaleffects qtsvg"
RDEPENDS_${PN} += " nxp-demo-experience-demos-list weston qtgraphicaleffects-qmlplugins qtquickcontrols-qmlplugins qtsvg-plugins"

do_install() {
    install -d -m 755 ${D}${bindir}
    install ${WORKDIR}/build/demoexperience ${D}${bindir}
}

FILES_${PN} += "${bindir}*"
