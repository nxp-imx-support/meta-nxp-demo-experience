SUMMARY = "GoPoint for i.MX Application Processors"
DESCRIPTION = "Launcher for GoPoint for i.MX Application Processors"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ece4d92215ee8177e48fbf5fc100ad20"

S = "${WORKDIR}/git"

SRCBRANCH = "lf-6.12.3_1.0.0"

NXP_DEMO_LIST_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience-demos-list.git;protocol=https"

SRC_URI = "${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};name=demos"

SRCREV = "${AUTOREV}"

PV = "lf-6.12.3_1.0.0+git${SRCREV}"

RDEPENDS:${PN} += "bash python3-packaging python3-paramiko iproute2 iproute2-tc python3-matplotlib "

do_install() {
    install -d -m 755 ${D}${GPNT_APPS_FOLDER}
    cp -r ${WORKDIR}/git/* ${D}${GPNT_APPS_FOLDER}
}

FILES:${PN} += "${GPNT_APPS_FOLDER}/* "
