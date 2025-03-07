L4T_DEB_COPYRIGHT_MD5 = "3d9212d4d5911fa3200298cd55ed6621"
DEPENDS = "tegra-libraries-core tegra-libraries-cuda tegra-libraries-multimedia-utils pango cairo glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base virtual/egl"

require tegra-debian-libraries-common.inc

LICENSE += "& MIT & BSD-3-Clause"
LIC_FILES_CHKSUM += "\
    file://usr/share/doc/nvidia-tegra/LICENSE.libnveventlib;md5=42479ac5ddc96ba7997ecf0636e707d2 \
    file://usr/share/doc/nvidia-tegra/LICENSE.libnvtracebuf;md5=42479ac5ddc96ba7997ecf0636e707d2 \
    file://usr/share/doc/nvidia-tegra/LICENSE.libnvjpeg;md5=1b873f8976e4e3683c04133e3035be98 \
"

MAINSUM = "55da515d66f472a2e07d8ce6e05a3c44d2ddd1d579aeb44e5a9469598de7188f"
MAINSUM:tegra210 = "ce237b46aa279914442d7120d4f2c9fd72affe54640ef26b878213b890fb3e6c"

inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

TEGRA_LIBRARIES_TO_INSTALL = "\
    tegra/libnvavp.so \
    tegra/libnvbufsurface.so.1.0.0 \
    tegra/libnvbufsurftransform.so.1.0.0 \
    tegra/libnvdecode2eglimage.so \
    tegra/libnvdsbufferpool.so.1.0.0 \
    tegra/libnveventlib.so \
    tegra/libnvexif.so \
    tegra/libnvid_mapper.so.1.0.0 \
    tegra/libnvjpeg.so \
    tegra/libnvmedia.so \
    tegra/libnvmm.so \
    tegra/libnvmm_contentpipe.so \
    tegra/libnvmm_parser.so \
    tegra/libnvmm_utils.so \
    tegra/libnvmmlite.so \
    tegra/libnvmmlite_image.so \
    tegra/libnvmmlite_utils.so \
    tegra/libnvmmlite_video.so \
    tegra/libnvofsdk.so \
    tegra/libnvosd.so \
    tegra/libnvparser.so \
    tegra/libnvtestresults.so \
    tegra/libnvtnr.so \
    tegra/libnvtracebuf.so \
    tegra/libnvtvmr.so \
"

do_install() {
    install_libraries
    for libname in nvdsbufferpool nvbufsurface nvbufsurftransform nvid_mapper; do
	ln -sf lib$libname.so.1.0.0 ${D}${libdir}/lib$libname.so.1
	ln -sf lib$libname.so.1.0.0 ${D}${libdir}/lib$libname.so
    done
}

PACKAGES =+ "${PN}-osd"
FILES_SOLIBSDEV = ""
SOLIBS = ".so*"
FILES:${PN}-osd = "${libdir}/libnvosd*"
RDEPENDS:${PN}-osd = "liberation-fonts"
