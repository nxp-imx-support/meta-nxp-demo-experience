
SUMMARY = "Core utilities for Python packages"
HOMEPAGE = "None"
AUTHOR = "None <Donald Stufft <donald@stufft.io>>"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.APACHE;md5=2ee41112a44fe7014dce33e26468ba93"

SRC_URI = "https://files.pythonhosted.org/packages/a1/d4/1fc4078c65507b51b96ca8f8c3ba19e6a61c8253c72794544580a7b6c24d/packaging-25.0.tar.gz"
SRC_URI[md5sum] = "ab0ef21ddebe09d1803575120d3f99f8"
SRC_URI[sha256sum] = "d443872c98d677bf60f6a1f2f8c1cb748e8fe762d2bf9d3148b5599295b0fc4f"

S = "${WORKDIR}/packaging-25.0"

RDEPENDS:${PN} = ""

inherit python_pep517
