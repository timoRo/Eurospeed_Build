DESCRIPTION = "OE-A Branding Lib for OE-A 2.0"
MAINTAINER = "oe-alliance team"
PACKAGE_ARCH = "${MACHINEBUILD}"

DEPENDS = "python"

require conf/license/license-gplv2.inc

inherit autotools-brokensep gitpkgv pythonnative

PACKAGES += " ${PN}-src"

SRCREV = "${AUTOREV}"
PV = "2.2+git${SRCPV}"
PKGV = "2.2+git${GITPKGV}"
PR = "r${DATETIME}"

SRC_URI="git://github.com/timoRo/branding-module.git;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-oever="${OE_VER}" \
    --with-distro="${DISTRO_NAME}" \
    --with-boxtype="${MACHINEBUILD}" \
    --with-brandoem="${BRAND_OEM}" \
    --with-machinebrand="${MACHINE_BRAND}" \
    --with-machinename="${MACHINE_NAME}" \
    --with-machinebuild="${MACHINE}" \
    --with-machinemake="${MACHINEBUILD}" \
    --with-imageversion="${DISTRO_VERSION}" \
    --with-imagebuild="${BUILD_VERSION}" \
    --with-imagedir="${IMAGEDIR}" \
    --with-imagefs="${IMAGE_FSTYPES}" \
    --with-mtdrootfs="${MTD_ROOTFS}" \
    --with-mtdkernel="${MTD_KERNEL}" \
    --with-rootfile="${ROOTFS_FILE}" \
    --with-kernelfile="${KERNEL_FILE}" \
    --with-mkubifs="${MKUBIFS_ARGS}" \
    --with-ubinize="${UBINIZE_ARGS}" \
    --with-driverdate="${DRIVERSDATE}" \
    "

do_configure_prepend() {
    if [ "${BRAND_OEM}" = "vuplus" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-VUPLUS-BASE}/recipes-drivers/vuplus-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "xtrend" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-XTREND-BASE}/recipes-drivers/et-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "entwopia" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-ENTWOPIA-BASE}/recipes-drivers/entwopia-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "dags1" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-DAGS-BASE}/recipes-drivers/dags-dvb-modules-7335.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "dags2" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-DAGS-BASE}/recipes-drivers/dags-dvb-modules-7335-ci.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "dags3" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-DAGS-BASE}/recipes-drivers/dags-dvb-modules-7356.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "gigablue" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-GIGABLUE-BASE}/recipes-drivers/gigablue-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "odin" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-ODIN-BASE}/recipes-drivers/odin-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "ini" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-INI-BASE}/recipes-drivers/ini-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "xp" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-XP-BASE}/recipes-drivers/xp-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "cube" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-CUBE-BASE}/recipes-drivers/e2bmc-dvb-modules-cube.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "ebox" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-EBOX-BASE}/recipes-drivers/ebox-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "ixuss" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-IXUSS-BASE}/recipes-drivers/ixuss-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "azbox" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-AZBOX-BASE}/recipes-drivers/azbox-dvb-modules.bb | cut -b 12-19`
    elif [ "${MACHINE}" = "blackbox7405" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-BLACKBOX-BASE}/recipes-drivers/blackbox-dvb-modules-blackbox7405.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "formuler" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-FORMULER-BASE}/recipes-drivers/formuler-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "skylake" ]; then
        DRIVERSDATE=`grep "SRCDATE = " ${OEA-META-SKYLAKE-BASE}/recipes-drivers/skylake-dvb-modules-${MACHINE}.bb | cut -b 12-19`
    elif [ "${BRAND_OEM}" = "dreambox" ]; then
        DRIVERSDATE="20140616"
    else
        DRIVERSDATE='N/A'
    fi
}

do_install_append() {
    install -d ${D}/usr/share/enigma2
    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes
    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static
    if [ ${MACHINEBUILD} = "ventonhdx" ]; then
        install -m 0644 ${S}/BoxBranding/boxes/uniboxhd1.jpg ${D}/usr/share/enigma2/uniboxhd1.jpg
        ln -sf /usr/share/enigma2/uniboxhd1.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/uniboxhd1.jpg
        install -m 0644 ${S}/BoxBranding/boxes/uniboxhd2.jpg ${D}/usr/share/enigma2/uniboxhd2.jpg
        ln -sf /usr/share/enigma2/uniboxhd2.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/uniboxhd2.jpg
        install -m 0644 ${S}/BoxBranding/boxes/uniboxhd3.jpg ${D}/usr/share/enigma2/uniboxhd3.jpg
        ln -sf /usr/share/enigma2/uniboxhd3.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/uniboxhd3.jpg
    elif [ ${MACHINE} = "et6x00" ]; then
        for f in ${S}/BoxBranding/boxes/et6*; do
            filename=$(basename "$f")
            extension="${filename##*.}"
            filename="${filename%.*}"
            install -m 0644 $f ${D}/usr/share/enigma2;
            ln -sf /usr/share/enigma2/$filename ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/$filename;
        done
    elif [ ${MACHINEBUILD} = "azboxhd" ]; then
        install -m 0644 ${S}/BoxBranding/boxes/elite.jpg ${D}/usr/share/enigma2/elite.jpg
        ln -sf /usr/share/enigma2/elite.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/elite.jpg
        install -m 0644 ${S}/BoxBranding/boxes/premium.jpg ${D}/usr/share/enigma2/premium.jpg
        ln -sf /usr/share/enigma2/premium.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/premium.jpg
        install -m 0644 ${S}/BoxBranding/boxes/premium+.jpg ${D}/usr/share/enigma2/premium+.jpg
        ln -sf /usr/share/enigma2/premium+.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/premium+.jpg
        install -m 0644 ${S}/BoxBranding/boxes/ultra.jpg ${D}/usr/share/enigma2/ultra.jpg
        ln -sf /usr/share/enigma2/ultra.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ultra.jpg
    elif [ ${MACHINEBUILD} = "xpeedlx" ]; then
        install -m 0644 ${S}/BoxBranding/boxes/xpeedlx1.jpg ${D}/usr/share/enigma2/xpeedlx1.jpg
        ln -sf /usr/share/enigma2/xpeedlx1.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/xpeedlx1.jpg
        install -m 0644 ${S}/BoxBranding/boxes/xpeedlx2.jpg ${D}/usr/share/enigma2/xpeedlx2.jpg
        ln -sf /usr/share/enigma2/xpeedlx2.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/xpeedlx2.jpg
    elif [ ${MACHINEBUILD} = "atemio6x00" ]; then
        install -m 0644 ${S}/BoxBranding/boxes/atemio6100.jpg ${D}/usr/share/enigma2/atemio6100.jpg
        ln -sf /usr/share/enigma2/atemio6100.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/atemio6100.jpg
        install -m 0644 ${S}/BoxBranding/boxes/atemio6200.jpg ${D}/usr/share/enigma2/atemio6200.jpg
        ln -sf /usr/share/enigma2/atemio6200.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/atemio6200.jpg        
    else
        install -m 0644 ${S}/BoxBranding/boxes/${MACHINEBUILD}.jpg ${D}/usr/share/enigma2/${MACHINEBUILD}.jpg
        ln -sf /usr/share/enigma2/${MACHINEBUILD}.jpg ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/${MACHINEBUILD}.jpg
    fi
    ln -sf /usr/share/enigma2/rc_models ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes
}

FILES_${PN}-src = "${libdir}/enigma2/python/Components/*.py"
FILES_${PN} = "${libdir}/enigma2/python/*.so /usr/share ${libdir}/enigma2/python/Components/*.pyo ${libdir}/enigma2/python/Plugins"
FILES_${PN}_openxta = "${libdir}/enigma2/python/*.so /usr/share ${libdir}/enigma2/python/Plugins"
FILES_${PN}-dev += "${libdir}/enigma2/python/*.la"
FILES_${PN}-staticdev += "${libdir}/enigma2/python/*.a"
FILES_${PN}-dbg += "${libdir}/enigma2/python/.debug"

