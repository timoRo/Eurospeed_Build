SUMMARY = "Merge machine and distro options to create a enigma2 machine task/package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

ALLOW_EMPTY_${PN} = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "1.0"
PR = "r9"

inherit packagegroup

DEPENDS = "enigma2-pliplugins mkdigital-feeds"

RRECOMMENDS_${PN} = "\
    eurospeed-version-info \
    enigma2-plugin-systemplugins-videomode \
    enigma2-plugin-extensions-graphmultiepg \
    enigma2-plugin-systemplugins-videotune \
    enigma2-plugin-settings-defaultsat \
    enigma2-plugin-extensions-mediaplayer \
    enigma2-plugin-extensions-dvdplayer \
    enigma2-plugin-systemplugins-devicemanager \
    enigma2-plugin-extensions-imdb \
    enigma2-plugin-systemplugins-fastscan \
    enigma2-plugin-systemplugins-3gmodemmanager \
    ${@base_contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
    "
