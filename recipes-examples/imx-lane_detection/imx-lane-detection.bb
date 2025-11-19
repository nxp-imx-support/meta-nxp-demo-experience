SUMMARY = "i.MX Lane Detection"
SECTION = "Machine Learning"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"
DEPENDS = "\
    cairo \
    glib-2.0 \
    gstreamer1.0 \
    nnstreamer \
    opencv \
"

SRC_URI = "${NXP_IMX_LANE_DETECTION_SRC};branch=${SRCBRANCH}"
NXP_IMX_LANE_DETECTION_SRC ?= "git://github.com/nxp-imx-support/imx-lane-detection.git;protocol=https"
SRCBRANCH = "master"
SRCREV = "07c4c8fe644218402b2105fd6acb67ec6ddad6ab"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DCMAKE_SYSROOT=${PKG_CONFIG_SYSROOT_DIR}"

IMX_LANE_DETECTION_DIR = "${GPNT_APPS_FOLDER}/scripts/machine_learning/imx_lane_detection"

do_install() {
    install -d ${D}${IMX_LANE_DETECTION_DIR}
    install -m 0755 ${B}/imx-lane-detection ${D}${IMX_LANE_DETECTION_DIR}
}

FILES:${PN} += "${IMX_LANE_DETECTION_DIR}"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
