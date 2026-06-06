SUMMARY = "Launcher for LLMs running on Ara-2"
DESCRIPTION = "NXP launcher designed for supported Large Language Models (LLMs) accelerated by the Ara240 DNPU at the edge"
HOMEPAGE = "https://github.com/nxp-imx-support/llm-edge-studio"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c527c8e980c6f87bb58ff58f3351e23d"

SRC_URI = "${LLM_EDGE_STUDIO_SRC};branch=${SRCBRANCH}"
LLM_EDGE_STUDIO_SRC ?= "git://github.com/nxp-imx-support/llm-edge-studio.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "aa5edaa6b9432eb9c1b5a077a7303eb2fe3da071"

inherit qt6-cmake

DEPENDS = " \
    qtbase \
    qtdeclarative \
    qtdeclarative-native \
"

# Build configuration matching build.sh
EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
"

# Point CMake to the correct source directory
OECMAKE_SOURCEPATH = "${S}"

do_install() {
    install -d ${D}/${datadir}/llm-edge-studio
    install -m 0755 ${B}/llm_edge_studio ${D}/${datadir}/llm-edge-studio

    install -d ${D}/${bindir}
    cp -r ${S}/llm-edge-studio/usr/share/llm-edge-studio ${D}/${datadir}
    install -m 0755 ${UNPACKDIR}/${BPN}-${PV}/llm-edge-studio/usr/bin/run_llm_edge_studio ${D}/${bindir}/run_llm_edge_studio

    install -d ${D}/${docdir}/llm-edge-studio
    cp -r ${S}/llm-edge-studio/usr/share/doc/llm-edge-studio ${D}/${docdir}

    install -m 0755 ${S}/scripts/launch_llm_edge_studio.sh ${D}${datadir}/llm-edge-studio/
}

FILES:${PN} = " \
    ${bindir} \
    ${datadir}/llm-edge-studio \
    ${docdir}/llm-edge-studio \
"

RDEPENDS:${PN} = " \
    imx-nxp-ara2 \
    eiq-aaf-connector \
    qtbase \
    qtdeclarative \
    bash \
"

# Skip QA warnings
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
