SUMMARY = "NXP Ara-2 vision examples for i.MX processors with Ara240 DNPU acceleration"
DESCRIPTION = "Real time video processing with AI/ML inference capabilities including object detection, classification, pose estimation, and semantic segmentation"
HOMEPAGE = "https://github.com/nxp-imx-support/ara2-vision-examples"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d8ff2d641cc45adce1b1882be29d1e35"

SRC_URI = "${ARA2_VISION_EXAMPLES_SRC};branch=${SRCBRANCH}"
ARA2_VISION_EXAMPLES_SRC ?= "git://github.com/nxp-imx-support/ara2-vision-examples.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "363f54e640fe44de2a007c14a69db68f719b76f1"

inherit cmake pkgconfig

DEPENDS = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    cairo \
    gtk+3 \
    imx-nxp-ara2 \
"

RDEPENDS:${PN} = " \
    imx-nxp-ara2 \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-bad \
    cairo \
    bash \
"

# Build configuration matching build.sh
EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
"
# Application directories (from build.sh)
MULTISTREAM_YOLO_APP_DIR = "tasks/object-detection/yolo/multistream-gstreamer"

# Point CMake to the correct subdirectory containing CMakeLists.txt
OECMAKE_SOURCEPATH = "${S}/${MULTISTREAM_YOLO_APP_DIR}"


do_configure:prepend() {
    export RTSDK_PATH="${RECIPE_SYSROOT}"
}

do_install:append() {
    install -d ${D}/

    cp -r ${S}/ara2-vision-examples/usr/ ${D}
}

FILES:${PN} = " \
    ${bindir}/ \
    ${bindir}/fetch_videos.sh \
    ${bindir}/multistream_yolo \
    ${bindir}/launcher_multistream_yolo \
    ${docdir}/${BPN} \
    ${datadir}/${BPN} \
    ${datadir}/cnn \
    ${datadir}/ara2-vision-examples/sample_videos/ \
"

# Skip QA warnings
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
