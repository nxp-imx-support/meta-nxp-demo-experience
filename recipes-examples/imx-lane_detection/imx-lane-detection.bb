SUMARY = "i.MX Lane Detection"
DESCRIPTION = "Recipe for i.MX Lane Detection on i.MX"
SECTION = "Machine Learning"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

IMX_LANE_DETCTION_DIR = "${GPNT_APPS_FOLDER}/scripts/machine_learning/imx_lane_detection"

NXP_IMX_LANE_DETECTION_SRC ?= "git://github.com/nxp-imx-support/imx-lane-detection.git;protocol=https"
SRCBRANCH = "master"
SRCREV = "07c4c8fe644218402b2105fd6acb67ec6ddad6ab"

SRC_URI = "${NXP_IMX_LANE_DETECTION_SRC};branch=${SRCBRANCH}"
S = "${WORKDIR}/git"

DEPENDS = "\
        glib-2.0 \
        gstreamer1.0 \
        nnstreamer \
        cairo \
		opencv \
"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DCMAKE_SYSROOT=${PKG_CONFIG_SYSROOT_DIR}"

do_install() {
    install -d ${D}${IMX_LANE_DETCTION_DIR}
    install -m 0755 ${WORKDIR}/build/imx-lane-detection ${D}${IMX_LANE_DETCTION_DIR}
}

FILES:${PN} += "${IMX_LANE_DETCTION_DIR}/imx-lane-detection"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
