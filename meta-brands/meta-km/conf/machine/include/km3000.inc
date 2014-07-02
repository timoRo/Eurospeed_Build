# We know about rtc
include conf/machine/include/build-extras.inc

BRAND_OEM = "km"

MACHINE_NAME = "\
${@base_contains('MACHINEBUILD', 'lx1-plus', 'xpeed' , '', d)}\


MACHINE_BRAND = "\
${@base_contains('MACHINEBUILD', 'lx1-plus', 'GoldenInterstar' , '', d)}\

"

IMAGEDIR ?= "\
${@base_contains('MACHINEBUILD', 'lx1-plus', 'lx1-plus' , '', d)}\


MTD_KERNEL = "\
${@base_contains('MACHINEBUILD', 'lx1-plus', 'mtd1' , '', d)}\

"

MTD_ROOTFS = "\
${@base_contains('MACHINEBUILD', 'lx1-plus', 'mtd2' , '', d)}\

"

KERNEL_FILE = "kernel.bin"
ROOTFS_FILE = "rootfs.bin"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = "\
    km-dvb-modules-${MACHINE} \
    "

DVBPROVIDER = "kernel"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
    kernel-module-cdfs \
    "

MACHINE_EXTRA_RRECOMMENDS = " \
    gst-plugin-dvbmediasink \
    "

include conf/machine/include/autoload-filesystems.inc

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dts --with-eac3"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

PREFERRED_PROVIDER_virtual/kernel = "linux-km3000"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "km-blindscan-dvbs-utils"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"
KERNEL_CONSOLE = "ttyS0,115200n8"

IMAGE_FSTYPES ?= "ubi"


IMAGE_CMD_ubi_prepend = " \
    rm -rf ${IMAGE_ROOTFS}/boot/*; \
    rm -rf ${IMAGE_ROOTFS}/tmp/*; \
    "

IMAGE_CMD_ubi_append = " \
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    ${@base_contains("MACHINE_FEATURES", "bootsplash", "cp ${DEPLOY_DIR_IMAGE}/${BOOTLOGO_FILENAME} ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${BOOTLOGO_FILENAME};" , "", d)} \
    mv ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${ROOTFS_FILE}; \
    gzip -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${KERNEL_FILE}; \
    echo ${DISTRO_NAME}-${DISTRO_VERSION}.${BUILD_VERSION} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    echo "rename this file to 'force' to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_usb.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ubifs; \
    rm -Rf ${IMAGEDIR}; \
    "


MACHINE_FEATURES += "3dtv alsa blindscan-dvbs hdmicec switchoff wifi bootsplash"

BOOTLOGO_FILENAME = "splash.bin"

require conf/machine/include/tune-mips32.inc

EXTRA_IMAGEDEPENDS += " \
    enigma2-plugin-systemplugins-remotecontrolcode \
    "

MACHINE_EXTRA_RDEPENDS_${PN} = " \
    enigma2-plugin-systemplugins-remotecontrolcode \
    "
