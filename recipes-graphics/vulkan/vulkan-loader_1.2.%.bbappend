REQUIRED_DISTRO_FEATURES:append:tegra = " x11"
PACKAGE_ARCH:tegra = "${TEGRA_PKGARCH}"
PACKAGECONFIG:remove:tegra = "wayland"
RRECOMMENDS:${PN}:remove:tegra = "mesa-vulkan-drivers"
RDEPENDS:${PN}:tegra = " tegra-libraries-vulkan"
