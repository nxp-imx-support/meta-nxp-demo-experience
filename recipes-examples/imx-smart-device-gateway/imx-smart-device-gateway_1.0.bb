SUMMARY = "i.MX Smart Device Gateway with Ara240 DNPU"
DESCRIPTION = "Recipe for i.MX Smart Device Gateway. Centralizes intelligence in one powerful server. Devices become voice-enabled endpoints—lightweight, affordable, and infinitely capable."
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c527c8e980c6f87bb58ff58f3351e23d"

SRC_URI = "${SMART_DEVICE_GATEWAY_SRC};branch=${SRCBRANCH}"
SRC_URI += "file://0001-Patch-to-smart-device-gateway-on-gopoint.patch"
SRC_URI += "file://install.sh"
SRC_URI += "file://launch_smart_device_gateway.sh"

SMART_DEVICE_GATEWAY_SRC ?= "git://github.com/nxp-imx-support/smart-device-gateway.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "1aca173846a1a518a3d2d5754f75eed704fe75d8"

inherit python_setuptools_build_meta

DEPENDS = " \
    python3-setuptools-native \
    python3-wheel-native \
    imx-nxp-ara2 \
"

RDEPENDS:${PN} += "bash"

do_install() {
    # Install python wheels
    install -d ${D}${datadir}/python-wheels
    install -m 0644 ${UNPACKDIR}/../dist/*.whl ${D}${datadir}/python-wheels

    # Install the main install.sh from files/ directory
    install -d ${D}${datadir}/smart-device-gateway
    install -m 0755 ${UNPACKDIR}/install.sh ${D}${datadir}/smart-device-gateway/install.sh

    # Install launcher script from files/ directory
    install -m 0755 ${UNPACKDIR}/launch_smart_device_gateway.sh ${D}${datadir}/smart-device-gateway/launch_smart_device_gateway.sh
    
    # Install demo_tools/install.sh as install_espeak-ng.sh
    install -m 0755 ${S}/demo_tools/install.sh ${D}${datadir}/smart-device-gateway/install_espeak-ng.sh

    # Install configuration files
    install -m 0644 ${S}/src/smart_device_gateway/conf/edge-voice.toml ${D}${datadir}/smart-device-gateway/
    install -m 0644 ${S}/src/smart_device_gateway/conf/config.yaml ${D}${datadir}/smart-device-gateway/
    install -m 0644 ${S}/src/smart_device_gateway/conf/server_config.json ${D}${datadir}/smart-device-gateway/
    install -m 0644 ${S}/LICENSE ${D}${datadir}/smart-device-gateway/

    # Install scripts to bindir
    install -d ${D}${bindir}
    install -m 0755 ${S}/demo_tools/run_client ${D}${bindir}/
    install -m 0755 ${S}/demo_tools/run_server ${D}${bindir}/
    install -m 0755 ${S}/demo_tools/run_server_only ${D}${bindir}/
}

FILES:${PN} += " \
	${datadir}/python-wheels \
	${datadir}/smart-device-gateway \
	${bindir}/run_client \
	${bindir}/run_server \
	${bindir}/run_server_only \
"

COMPATIBLE_MACHINE = "(mx8-nxp-bsp|mx9-nxp-bsp)"
