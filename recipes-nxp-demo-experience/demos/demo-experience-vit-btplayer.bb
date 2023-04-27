# Copyright 2020-2023 NXP

DESCRIPTION = "Example of Voice Intelligent Technology"
SECTION = "multimedia"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=5a0bf11f745e68024f37b4724a5364fe"

SRCBRANCH = "MM_04.07.02_2210_L5.15.y"
BTDEMODIR = "/home/root/.nxp-demo-experience/scripts/multimedia/btplayerdemo"

SRC_URI = "git://github.com/nxp-imx/imx-voiceui.git;protocol=https;branch=${SRCBRANCH} \
					file://0001-Update-ExApp-Makefile-to-Build-Demo.patch \
					file://0001-Add-Functionality-for-VITBTPlayer.patch \
                                        file://0001-Add-i.MX93-support.patch \
					file://0002-Modify-Makefile.patch \
	  			"
SRCREV = "ea810018d436a47fa19b40910cc6ce427c43bfc9"

S = "${WORKDIR}/git"

DEPENDS += " \
portaudio-v19 \
dbus \
dbus-glib \
dbus-glib-native \
glib-2.0 \
alsa-lib \
nxp-afe"

RDEPENDS:${PN} = "nxp-afe-voiceseeker"

EXTRA_CONF = "--enable-armv8 --bindir=/unit_tests/ --libdir=/usr/lib/"

EXTRA_OEMAKE:mx8-nxp-bsp = "BUILD_ARCH=CortexA53"
EXTRA_OEMAKE:mx93-nxp-bsp = "BUILD_ARCH=CortexA55"


do_compile() {
	cd ${S}/vit/i.MX8M_A53/ExApp
        make
        cd ${S}/vit/i.MX9X_A55/ExApp
        make
}

do_install() {
	install -d -m 0755 ${D}${BTDEMODIR}
        install -d -m 0755 ${D}${BTDEMODIR}/i.MX8M_A53
        install -d -m 0755 ${D}${BTDEMODIR}/i.MX9X_A55
	install -m 0755 ${S}/vit/i.MX8M_A53/ExApp/build/btp_vit ${D}${BTDEMODIR}/i.MX8M_A53
        install -m 0755 ${S}/vit/i.MX8M_A53/ExApp/build/btp_vit ${D}${BTDEMODIR}/i.MX9X_A55
}

FILES:${PN} += "${BTDEMODIR}/i.MX8M_A53/btp_vit"
FILES:${PN} += "${BTDEMODIR}/i.MX9X_A55/btp_vit"

INSANE_SKIP_${PN} += "ldflags"
TARGET_CC_ARCH += "${LDFLAGS}"
