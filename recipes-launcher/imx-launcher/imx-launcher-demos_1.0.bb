# Copyright 2019 NXP

SUMMARY = "i.MX Launcher Demos"
DESCRIPTION = "Demos for i.MX Launcher application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://source.codeaurora.org/external/imxsupport/imx-demo-launcher-demos-list"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_install() {
    install -d -m 755 ${D}/home/root/.imx-launcher
    cp -r ${S}/* ${D}/home/root/.imx-launcher
}

FILES_${PN} += "/home/root/.imx-launcher/*"
