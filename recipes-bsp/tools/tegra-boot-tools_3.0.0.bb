DESCRIPTION = "Boot-related tools for Tegra platforms"
HOMEPAGE = "https://github.com/OE4T/tegra-boot-tools"
LICENSE = "MIT & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7a9217de7f233011b127382da9a035a1"

DEPENDS = "zlib util-linux-libuuid systemd tegra-eeprom-tool"

SRC_URI = "https://github.com/OE4T/${BPN}/releases/download/v${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "2480eaf129e95b8f892c6cb4e1768f6b302d8e3f580866b6a59516c04a3ae6a3"

OTABOOTDEV ??= "/dev/mmcblk0boot0"
OTAGPTDEV ??= "/dev/mmcblk0boot1"

EXTRA_OECMAKE = "-DSYSTEMD_SYSTEM_UNITDIR=${systemd_system_unitdir} \
                 -DMACHINE=${MACHINE} \
                 -DBOOT_DEVICE=${OTABOOTDEV} -DGPT_DEVICE=${OTAGPTDEV}"

inherit cmake pkgconfig systemd features_check

REQUIRED_DISTRO_FEATURES = "systemd"

SYSTEMD_PACKAGES = "${PN}-earlyboot ${PN}-lateboot"

PACKAGES =+ "libtegra-boot-tools ${PN}-earlyboot ${PN}-lateboot ${PN}-updater ${PN}-nvbootctrl ${PN}-nv-update-engine"

SYSTEMD_SERVICE:${PN}-earlyboot = "bootcountcheck.service"
SYSTEMD_SERVICE:${PN}-lateboot = "update_bootinfo.service"

FILES:libtegra-boot-tools = "${libdir}/libtegra-boot-tools${SOLIBS} ${datadir}/tegra-boot-tools"

FILES:${PN}-earlyboot = "${sbindir}/bootcountcheck"
RDEPENDS:${PN}-earlyboot = "${PN}"

RDEPENDS:${PN}-lateboot = "${PN}"

FILES:${PN}-updater = "${bindir}/tegra-bootloader-update"
RDEPENDS:${PN}-updater = "${PN} tegra-bootpart-config"

FILES:${PN} += "${libdir}/tmpfiles.d"

FILES:${PN}-nvbootctrl = "${sbindir}/nvbootctrl"
RDEPENDS:${PN}-nvbootctrl = "${PN}"
RCONFLICTS:${PN}-nvbootctrl = "tegra-redundant-boot-nvbootctrl"
FILES:${PN}-nv-update-engine = "${sbindir}/nv_update_engine"
RDEPENDS:${PN}-nv-update-engine = "${PN}-updater ${PN}"
RCONFLICTS:${PN}-nv-update-engine = "tegra-redundant-boot-base"

PACKAGE_ARCH = "${MACHINE_ARCH}"
