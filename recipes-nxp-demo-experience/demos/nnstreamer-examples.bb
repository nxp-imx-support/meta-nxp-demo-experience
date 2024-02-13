SUMARY = "NNStreamer Examples"
DESCRIPTION = "Recipe for NNStreamer Examples on i.MX"
SECTION = "Machine Learning"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3d5621953a6b13048ccb5e891b99e00e"

IMX_NNSTREANER_DIR = "/home/root/.nxp-demo-experience/scripts/machine_learning/nnstreamer"

NXP_IMX_NNSTREANER_SRC ?= "git://github.com/nxp-imx/nxp-nnstreamer-examples.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "1450073874cf6ef0be85eeca407bb3b0f2b4ba1a"

SRC_URI = "${NXP_IMX_NNSTREANER_SRC};branch=${SRCBRANCH}"
S = "${WORKDIR}/git"

DEPENDS = "\
        glib-2.0 \
        gstreamer1.0 \
        nnstreamer \
"

RDEPENDS:${PN} = "\
        glib-2.0 \
        gstreamer1.0 \
        nnstreamer \
        bash \
"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DCMAKE_SYSROOT=${PKG_CONFIG_SYSROOT_DIR}"

do_install() {
    install -d ${D}${IMX_NNSTREANER_DIR}

    install -d ${D}${IMX_NNSTREANER_DIR}/common
    cp -r ${WORKDIR}/git/common/* ${D}${IMX_NNSTREANER_DIR}/common
    cp ${WORKDIR}/git/LICENSE ${D}${IMX_NNSTREANER_DIR}
    cp ${WORKDIR}/git/README.md ${D}${IMX_NNSTREANER_DIR}
    cp ${WORKDIR}/git/SCR-1.3.txt ${D}${IMX_NNSTREANER_DIR}

    install -d ${D}${IMX_NNSTREANER_DIR}/classification
    cp ${WORKDIR}/git/classification/README.md ${D}${IMX_NNSTREANER_DIR}/classification
    install -m 0755 ${WORKDIR}/git/classification/classification_utils.sh ${D}${IMX_NNSTREANER_DIR}/classification
    install -m 0755 ${WORKDIR}/git/classification/example_classification_mobilenet_v1_tflite.sh ${D}${IMX_NNSTREANER_DIR}/classification
    install -m 0755 ${WORKDIR}/build/classification/example_classification_mobilenet_v1_tflite ${D}${IMX_NNSTREANER_DIR}/classification

    install -d ${D}${IMX_NNSTREANER_DIR}/detection
    cp ${WORKDIR}/git/detection/README.md ${D}${IMX_NNSTREANER_DIR}/detection
    install -m 0755 ${WORKDIR}/git/detection/detection_utils.sh ${D}${IMX_NNSTREANER_DIR}/detection
    install -m 0755 ${WORKDIR}/git/detection/example_detection_mobilenet_ssd_v2_tflite.sh ${D}${IMX_NNSTREANER_DIR}/detection

    install -d ${D}${IMX_NNSTREANER_DIR}/pose
    cp ${WORKDIR}/git/pose/README.md ${D}${IMX_NNSTREANER_DIR}/pose
    install -m 0755 ${WORKDIR}/git/pose/example_pose_movenet_tflite.py ${D}${IMX_NNSTREANER_DIR}/pose
    
}

FILES:${PN} += "${IMX_NNSTREANER_DIR}/*"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
