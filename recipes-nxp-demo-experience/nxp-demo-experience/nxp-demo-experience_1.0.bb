# Copyright 2020 NXP

SUMMARY = "NXP Demo Experience"
DESCRIPTION = "Launcher for NXP Demo Experience"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

S = "${WORKDIR}/git"

SRCBRANCH = "zeus-5.4.24-2.1.0"
NXP_DEMO_SRC ?= "git://source.codeaurora.org/external/imxsupport/nxp-demo-experience;protocol=https"
NXP_DEMO_LIST_SRC ?= "git://source.codeaurora.org/external/imxsupport/nxp-demo-experience-demos-list;protocol=https"
NXP_DEMO_WESTON_SRC ?= "file://weston.ini"

SRC_URI = " \
    ${NXP_DEMO_SRC};branch=${SRCBRANCH};name=nxp-demo-experience \
    ${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};destsuffix=demos;name=demos \
    ${NXP_DEMO_WESTON_SRC};destsuffix=icon;name=icon "

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

do_install_append_mx8mp() {
    install -d -m 755 ${D}${sysconfdir}/xdg/weston/
    cp ${BASE_WORKDIR}/${MACHINE_ARCH}-poky-linux/weston-init/1.0-r0/image${sysconfdir}/xdg/weston/weston.ini ${D}${sysconfdir}/xdg/weston/weston.ini
    rm ${BASE_WORKDIR}/${MACHINE_ARCH}-poky-linux/weston-init/1.0-r0/image${sysconfdir}/xdg/weston/weston.ini
    echo "\n[launcher]\nicon=/home/root/.nxp-demo-experience/icon/icon_demo_launcher.png\npath=/usr/bin/demoexperience\n\n[launcher]\nicon=/usr/share/weston/terminal.png\npath=/usr/bin/weston-terminal" >> ${BASE_WORKDIR}/${MACHINE_ARCH}-poky-linux/weston-init/1.0-r0/image${sysconfdir}/xdg/weston/weston.ini
}

FILES_${PN} += "${bindir}* /home/root/.nxp-demo-experience/* ${sysconfdir}/xdg/weston/"
