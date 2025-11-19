SUMMARY = "NNStreamer Examples"
DESCRIPTION = "Recipe for i.MX NNStreamer Examples"
SECTION = "Machine Learning"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=df2d5c27ffc38b06ea00cd3edc2b4572"

IMX_NNSTREANER_DIR = "${GPNT_APPS_FOLDER}/scripts/machine_learning/nnstreamer"

NXP_NNSTREAMER_EXAMPLES_SRC ?= "git://github.com/nxp-imx/nxp-nnstreamer-examples.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "062ebd146f6519c437db6516f257f20d63dda1dd"

SRC_URI = "${NXP_NNSTREAMER_EXAMPLES_SRC};branch=${SRCBRANCH}"
S = "${WORKDIR}/git"

DEPENDS = "\
        tensorflow-lite \
        glib-2.0 \
        gstreamer1.0 \
        nnstreamer \
"

RDEPENDS:${PN} = "\
        tensorflow-lite \
        glib-2.0 \
        gstreamer1.0 \
        nnstreamer \
        bash \
"

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DCMAKE_SYSROOT=${PKG_CONFIG_SYSROOT_DIR}"

do_install() {
    install -d ${D}${IMX_NNSTREANER_DIR}

    cp ${WORKDIR}/git/LICENSE ${D}${IMX_NNSTREANER_DIR}
    cp ${WORKDIR}/git/SCR*.txt ${D}${IMX_NNSTREANER_DIR}

    install -d ${D}${IMX_NNSTREANER_DIR}/classification
    install -m 0755 ${WORKDIR}/build/classification/example_classification_mobilenet_v1_tflite ${D}${IMX_NNSTREANER_DIR}/classification

    install -d ${D}${IMX_NNSTREANER_DIR}/classification_detection
    install -m 0755 ${WORKDIR}/build/mixed-demos/example_classification_and_detection_tflite ${D}${IMX_NNSTREANER_DIR}/classification_detection

    install -d ${D}${IMX_NNSTREANER_DIR}/dual_classification
    install -m 0755 ${WORKDIR}/build/mixed-demos/example_double_classification_tflite ${D}${IMX_NNSTREANER_DIR}/dual_classification

    install -d ${D}${IMX_NNSTREANER_DIR}/emotion_detection
    install -m 0755 ${WORKDIR}/build/face-processing/example_emotion_classification_tflite ${D}${IMX_NNSTREANER_DIR}/emotion_detection

    install -d ${D}${IMX_NNSTREANER_DIR}/face_detection
    install -m 0755 ${WORKDIR}/build/face-processing/example_face_detection_tflite ${D}${IMX_NNSTREANER_DIR}/face_detection

    install -d ${D}${IMX_NNSTREANER_DIR}/object_detection
    install -m 0755 ${WORKDIR}/build/object-detection/example_detection_mobilenet_ssd_v2_tflite ${D}${IMX_NNSTREANER_DIR}/object_detection

    install -d ${D}${IMX_NNSTREANER_DIR}/pose_estimation
    install -m 0755 ${WORKDIR}/build/pose-estimation/example_pose_movenet_tflite ${D}${IMX_NNSTREANER_DIR}/pose_estimation

    install -d ${D}${IMX_NNSTREANER_DIR}/pose_face
    install -m 0755 ${WORKDIR}/build/mixed-demos/example_face_and_pose_detection_tflite ${D}${IMX_NNSTREANER_DIR}/pose_face

    install -d ${D}${IMX_NNSTREANER_DIR}/semantic_segmentation
    install -m 0755 ${WORKDIR}/build/semantic-segmentation/example_segmentation_deeplab_v3_tflite ${D}${IMX_NNSTREANER_DIR}/semantic_segmentation
    
}

FILES:${PN} += "${IMX_NNSTREANER_DIR}/*"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
