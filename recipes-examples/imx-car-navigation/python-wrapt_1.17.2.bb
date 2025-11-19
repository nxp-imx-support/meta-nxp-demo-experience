
SUMMARY = "Module for decorators, wrappers and monkey patching."
HOMEPAGE = "https://github.com/GrahamDumpleton/wrapt"
AUTHOR = "Graham Dumpleton <Graham.Dumpleton@gmail.com>"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc34cbad60bc961452eb7ade801d25f7"

SRC_URI = "https://files.pythonhosted.org/packages/c3/fc/e91cc220803d7bc4db93fb02facd8461c37364151b8494762cc88b0fbcef/wrapt-1.17.2.tar.gz"
SRC_URI[md5sum] = "f4db93e73e5c70a59955f0ec162d585d"
SRC_URI[sha256sum] = "41388e9d4d1522446fe79d3213196bd9e3b301a336965b9e27ca2788ebd122f3"

S = "${WORKDIR}/wrapt-1.17.2"

RDEPENDS:${PN} = ""

inherit setuptools3
