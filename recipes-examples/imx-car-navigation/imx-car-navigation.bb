DESCRIPTION = "Yocto recipe to install canopen and python-can, clone CANopenLinux repository, initialize and update submodules, and run make and make install commands"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit setuptools3

SRC_URI = "git://github.com/CANopenNode/CANopenLinux.git;branch=master;protocol=https"
SRCREV = "52ffe7acb039dd669bc523440272296081fa8f00"

TARGET_CC_ARCH += "${LDFLAGS}"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[network] = "1"

do_compile() {
        cd ${S}
        git submodule update --init --recursive
        oe_runmake

        cd ${S}/cocomm
        oe_runmake
}

do_install() {
        install -d ${D}${bindir}
        install -m 0755 ${S}/canopend ${D}${bindir}/canopend
        install -m 0755 ${S}/cocomm/cocomm ${D}${bindir}/cocomm
}