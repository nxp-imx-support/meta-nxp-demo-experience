
SUMMARY = "Backported and Experimental Type Hints for Python 3.8+"
HOMEPAGE = "None"
AUTHOR = "None <"Guido van Rossum, Jukka Lehtosalo, Łukasz Langa, Michael Lee" <levkivskyi@gmail.com>>"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fcf6b249c2641540219a727f35d8d2c2"

SRC_URI = "https://files.pythonhosted.org/packages/f6/37/23083fcd6e35492953e8d2aaaa68b860eb422b34627b13f2ce3eb6106061/typing_extensions-4.13.2.tar.gz"
SRC_URI[md5sum] = "58226788d248cee8d6283ee616543975"
SRC_URI[sha256sum] = "e6c81219bd689f51865d9e372991c540bda33a0379d5573cddb9a3a23f7caaef"

S = "${WORKDIR}/typing_extensions-4.13.2"

RDEPENDS:${PN} = ""

inherit python_pep517
