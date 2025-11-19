# Copyright 2024 NXP
DESCRIPTION = "Package for GoPoint for i.MX Application Processors"

inherit packagegroup

DEMOS ?= "gopoint-base-apps "

DEMOS:append:mx8qm-nxp-bsp = " imx-nnstreamer-examples \
                               imx-video-to-texture \
"
DEMOS:append:mx8mp-nxp-bsp = " imx-voice-example \
                        imx-smart-kitchen \
                        imx-voice-player \
                        imx-smart-fitness \
                        imx-nnstreamer-examples \
                        imx-ebike \
                        imx-lane-detection \
                        imx-car-navigation \
                        imx-camera-rotation \
"
DEMOS:append:mx8mm-nxp-bsp = " imx-voice-example \
                        imx-smart-kitchen \
                        imx-voice-player \
                        imx-nnstreamer-examples \
                        imx-ebike \
                        imx-camera-rotation \
"
DEMOS:append:mx93-nxp-bsp = "  imx-voice-player \
                        imx-smart-kitchen \
                        imx-nnstreamer-examples \
                        imx-ebike \
                        imx-ele-app \
                        imx-smart-fitness \
                        imx-car-navigation \
"
DEMOS:append:mx95-nxp-bsp = " imx-video-to-texture \
                        imx-nnstreamer-examples \
                        imx-camera-rotation \
"
DEMOS:append:mx8ulp-nxp-bsp = " imx-camera-rotation \
"

RDEPENDS:${PN} += "imx-gopoint ${DEMOS}"
