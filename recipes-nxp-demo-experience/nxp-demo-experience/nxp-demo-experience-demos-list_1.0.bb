# Copyright 2020 NXP

SUMMARY = "NXP Demo Experience Demos List"
DESCRIPTION = "List of Demos for NXP Demo Experience"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://source.codeaurora.org/external/imxsupport/nxp-demo-experience-demos-list;branch=warrior-4.19.35-1.1.0"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

RDEPENDS_${PN} += "bash"

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience
    cp -r ${S}/* ${D}/home/root/.nxp-demo-experience
}

FILES_${PN} += "/home/root/.nxp-demo-experience/*"
