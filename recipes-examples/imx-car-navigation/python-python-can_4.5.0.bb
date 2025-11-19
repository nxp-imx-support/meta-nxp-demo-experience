
SUMMARY = "Controller Area Network interface module for Python"
HOMEPAGE = "None"
AUTHOR = "python-can contributors <None>"
LICENSE = "LGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "https://files.pythonhosted.org/packages/6a/4b/b6fd103c3f2eb0ae942e0704642d396ebbaf87f4d82d0102560cc738fdf1/python_can-4.5.0.tar.gz"
SRC_URI[md5sum] = "e22638e3e5d102e428614715dd04910f"
SRC_URI[sha256sum] = "d3684cebe5b028a148c1742b3a45cec4fcaf83a7f7c52d0680b2eaeaf52f8eb7"

S = "${WORKDIR}/python_can-4.5.0"

RDEPENDS:${PN} = "python-wrapt python-packaging python-typing-extensions"

inherit python_pep517
