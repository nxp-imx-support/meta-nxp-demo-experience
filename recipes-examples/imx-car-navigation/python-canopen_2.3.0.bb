
SUMMARY = "CANopen stack implementation"
HOMEPAGE = "None"
AUTHOR = "None <Christian Sandberg <christiansandberg@me.com>, André Colomb <src@andre.colomb.de>, André Filipe Silva <afsilva.work@gmail.com>>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=97f135a6ee6f800c377b5512122c7a8d"

SRC_URI = "https://files.pythonhosted.org/packages/1a/b3/733e5f98c995d7f3e82853bc5ee2f0677df6203d51d8a4387af188322523/canopen-2.3.0.tar.gz"
SRC_URI[md5sum] = "808c04aa0394210be9eeb46fb6e3bf96"
SRC_URI[sha256sum] = "792084a93c138d5b2a406ddd2d4eb5ce208f03bd8fda60f81ad2bb8d56c6b827"

S = "${WORKDIR}/canopen-2.3.0"

RDEPENDS:${PN} = "python-python-can"

inherit setuptools3
