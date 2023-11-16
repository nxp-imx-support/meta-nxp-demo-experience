SUMARY = "i.MX Smart Fitness"
DESCRIPTION = "Recipe for i.MX Smart Fitness application on i.MX"
SECTION = "Machine Learning"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

IMX_SMART_FITNESS_DIR = "/home/root/.nxp-demo-experience/scripts/machine_learning/imx_smart_fitness"

NXP_IMX_SMART_FITNESS_SRC ?= "git://github.com/nxp-imx-support/imx-smart-fitness.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "a6e12b8f5317968ba059519acf91b34fc73544b0"

SRC_URI = "${NXP_IMX_SMART_FITNESS_SRC};branch=${SRCBRANCH}"
S = "${WORKDIR}/git"

DEPENDS = "\
        glib-2.0 \
        gstreamer1.0 \
        nnstreamer \
        cairo \
"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DCMAKE_SYSROOT=${PKG_CONFIG_SYSROOT_DIR}"

do_install() {
    install -d ${D}${IMX_SMART_FITNESS_DIR}
    install -m 0755 ${WORKDIR}/build/src/imx-smart-fitness ${D}${IMX_SMART_FITNESS_DIR}
}

FILES:${PN} += "${IMX_SMART_FITNESS_DIR}/imx-smart-fitness"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
