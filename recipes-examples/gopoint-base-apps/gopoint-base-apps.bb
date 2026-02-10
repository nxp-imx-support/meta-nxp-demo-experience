SUMMARY = "GoPoint for i.MX Application Processors"
DESCRIPTION = "Launcher for GoPoint for i.MX Application Processors"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d8ff2d641cc45adce1b1882be29d1e35"


SRCBRANCH = "lf-6.18.2_1.0.0"

NXP_DEMO_LIST_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience-demos-list.git;protocol=https"

SRC_URI = "${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};name=demos"

SRCREV = "8610a9de784c62f0d7ae6d6404511e5305c7ffda"

PV = "lf-6.18.2_1.0.0+git${SRCREV}"

RDEPENDS:${PN} += "bash perl python3-packaging python3-paramiko iproute2 iproute2-tc python3-matplotlib "

do_install() {
    install -d -m 755 ${D}${GPNT_APPS_FOLDER}
    cp -r ${B}/* ${D}${GPNT_APPS_FOLDER}
}

FILES:${PN} += "${GPNT_APPS_FOLDER}/* "
