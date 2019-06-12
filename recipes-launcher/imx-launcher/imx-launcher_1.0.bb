# Copyright 2019 NXP

SUMMARY = "i.MX Demo Launcher"
DESCRIPTION = "Launcher for i.MX demo applications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://source.codeaurora.org/external/imxsupport/imx-demo-launcher"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit qmake5

DEPENDS += "qtbase qtquickcontrols2 qtconnectivity qtquick1 imx-launcher-demos weston"

do_install() {
    install -d -m 755 ${D}${bindir}
    install ${WORKDIR}/build/demolauncher ${D}${bindir}
    install -d -m 755 ${D}/home/root/.imx-launcher
    cp ${WORKDIR}/git/images/icon_demo_launcher.png ${D}/home/root/.imx-launcher/
}

FILES_${PN} += "${bindir}* /home/root/.imx-launcher/*"
