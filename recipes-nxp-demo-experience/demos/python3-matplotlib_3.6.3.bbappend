SRC_URI:remove = "http://www.qhull.org/download/qhull-2020-src-8.0.2.tgz;name=qhull;subdir=matplotlib-${PV}/build"
SRC_URI += "git://github.com/qhull/qhull.git;protocol=https;name=qhull;subdir=matplotlib-${PV}/build/qhull-2020.2;branch=master"
SRCREV_qhull = "613debeaea72ee66626dace9ba1a2eff11b5d37d"
