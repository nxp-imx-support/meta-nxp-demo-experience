SUMMARY = "i.MX Video to Texture"
DESCRIPTION = "Recipe for i.MX Video to Texture application on i.MX"
SECTION = "Multimedia"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=181b016daba4e19bb10652a281c52a70"

IMX_VIDEO_TO_TEXTURE_DIR = "${GPNT_APPS_FOLDER}/scripts/multimedia/imx_video_to_texture"

NXP_IMX_VIDEO_TO_TEXTURE_SRC ?= "git://github.com/nxp-imx-support/imx-video-to-texture.git;protocol=https"
SRCBRANCH = "main"

SRC_URI = "${NXP_IMX_VIDEO_TO_TEXTURE_SRC};branch=${SRCBRANCH}"
S = "${WORKDIR}/git"
SRCREV = "ee77a45391994177934ce19b15c554be866a49ff"

DEPENDS = "\
        glib-2.0 \
        gstreamer1.0 \
        gstreamer1.0-plugins-good \
        packagegroup-qt6-imx \
        qtbase \
    	qtdeclarative \
    	qtdeclarative-native \
"

inherit pkgconfig qt6-cmake

EXTRA_OECMAKE = "-DCMAKE_SYSROOT=${PKG_CONFIG_SYSROOT_DIR}"


do_install() {
    install -d ${D}${IMX_VIDEO_TO_TEXTURE_DIR}
    install -m 0755 ${WORKDIR}/build/imx-video-to-texture ${D}${IMX_VIDEO_TO_TEXTURE_DIR}
}

FILES:${PN} += "${IMX_VIDEO_TO_TEXTURE_DIR}/imx-video-to-texture"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
