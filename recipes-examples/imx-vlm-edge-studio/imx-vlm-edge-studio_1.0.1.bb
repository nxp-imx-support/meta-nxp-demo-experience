SUMMARY = "Qt/QML-based application designed for NXP's FRDM i.MX platforms to interact with Vision Models accelerated by the Ara240 DNPU at the edge"
DESCRIPTION = "NXP launcher designed for supported Visual Language Models (VLMs) accelerated by the Ara240 DNPU at the edge"
HOMEPAGE = "https://github.com/nxp-imx-support/vlm-edge-studio"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d8ff2d641cc45adce1b1882be29d1e35"

DEPENDS = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-bad \
    qtbase \
    qtdeclarative \
    qtdeclarative-native \
    qtmultimedia \
    virtual/libgles2 \
    virtual/egl \
"

SRC_URI = "${VLM_EDGE_STUDIO_SRC};branch=${SRCBRANCH}"
VLM_EDGE_STUDIO_SRC ?= "git://github.com/nxp-imx-support/vlm-edge-studio.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "a59be29f804336df972d7d4ca6c0829dcf214380"

inherit qt6-cmake

# Use OpenGL ES instead of desktop OpenGL for embedded platforms
EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
    -DUSE_GLES=ON \
    -DOpenGL_GL_PREFERENCE=GLVND \
    -DOPENGL_INCLUDE_DIR=${STAGING_INCDIR} \
    -DOPENGL_opengl_LIBRARY=${STAGING_LIBDIR}/libGLESv2.so \
    -DOPENGL_glx_LIBRARY=${STAGING_LIBDIR}/libEGL.so \
    -DOPENGL_egl_LIBRARY=${STAGING_LIBDIR}/libEGL.so \
    -DOPENGL_gles2_LIBRARY=${STAGING_LIBDIR}/libGLESv2.so \
    -DOPENGL_gles3_LIBRARY=${STAGING_LIBDIR}/libGLESv2.so \
"

# Point CMake to the correct source directory
OECMAKE_SOURCEPATH = "${S}"

do_install() {
    install -d ${D}/${datadir}/vlm-edge-studio
    cp -r ${S}/vlm-edge-studio/usr/share/vlm-edge-studio/ ${D}/${datadir}
    install -m 0755 ${B}/bin/vlm_edge_studio ${D}/${datadir}/vlm-edge-studio

    install -d ${D}/${docdir}/vlm-edge-studio
    cp -r ${S}/vlm-edge-studio/usr/share/doc/vlm-edge-studio ${D}/${docdir}

    install -d ${D}/${bindir}
    install -m 0755 ${S}/vlm-edge-studio/usr/share/vlm-edge-studio/scripts/run_vlm_edge_studio ${D}/${bindir}/run_vlm_edge_studio
    install -m 0755 ${S}/scripts/launch_vlm_edge_studio.sh ${D}${datadir}/vlm-edge-studio/
}

FILES:${PN} = " \
    ${bindir} \
    ${datadir}/vlm-edge-studio \
    ${docdir}/vlm-edge-studio \
"

RDEPENDS:${PN} = " \
    imx-nxp-ara2 \
    eiq-aaf-connector \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-bad \
    qtbase \
    qtdeclarative \
    qtmultimedia \
    bash \
"

# Skip QA warnings
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
