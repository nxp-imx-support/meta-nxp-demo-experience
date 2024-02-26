SUMMARY = "GoPoint for i.MX Application Processors"
DESCRIPTION = "Launcher for GoPoint for i.MX Application Processors"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8414149b19eabc57ab1350562aa605b1"

S = "${WORKDIR}/git"

SRCBRANCH = "lf-6.6.3_1.0.0"

NXP_DEMO_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience.git;protocol=https"
NXP_DEMO_LIST_SRC ?= "git://github.com/nxp-imx-support/nxp-demo-experience-demos-list.git;protocol=https"

SRC_URI = " \
    ${NXP_DEMO_SRC};branch=${SRCBRANCH};name=nxp-demo-experience \
    ${NXP_DEMO_LIST_SRC};branch=${SRCBRANCH};destsuffix=demos;name=demos "

SRCREV_FORMAT = "nxp-demo-experience_demos"

SRCREV_nxp-demo-experience = "70d4df66c0e5448bfd80fc138d8ee508423e711d"
SRCREV_demos = "e1ff4cbe0f645457fb6f5041b26559b0ff2b02bc"

PV = "lf-6.6.3_1.0.0+git${SRCPV}"

inherit qt6-qmake

DEMOS ?= ""

DEMOS:mx8qm-nxp-bsp = " nnstreamer-examples \
"

DEMOS:mx8mp-nxp-bsp = " demo-experience-voice-demo \
                        demo-experience-voice-player \
                        demo-experience-smart-kitchen \
                        demo-experience-imx-smart-fitness \
                        nnstreamer-examples \
"
DEMOS:mx8mm-nxp-bsp = " demo-experience-voice-demo \
                        demo-experience-voice-player \
                        demo-experience-smart-kitchen \
                        nnstreamer-examples \
"
DEMOS:mx93-nxp-bsp = "  demo-experience-voice-player \
                        demo-experience-smart-kitchen \
                        demo-experience-imx-smart-fitness \
                        nnstreamer-examples \
"

DEPENDS += " packagegroup-qt6-imx qtconnectivity qtsvg"
RDEPENDS:${PN} += " weston bash qtsvg-plugins qt5compat ${DEMOS} python3-packaging python3-paramiko iproute2 iproute2-tc python3-matplotlib "

do_install() {
    install -d -m 755 ${D}/home/root/.nxp-demo-experience
    cp -r ${WORKDIR}/demos/* ${D}/home/root/.nxp-demo-experience

    install -d -m 755 ${D}${bindir}
    install ${WORKDIR}/build/demoexperience ${D}${bindir}

    ln -sfr ${D}${bindir}/demoexperience ${D}${bindir}/gopoint
}

FILES:${PN} += "${bindir}* /home/root/.nxp-demo-experience/* "
