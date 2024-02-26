# GoPoint for i.MX Applications Processors - Meta Layer

[![License badge](https://img.shields.io/badge/License-MIT-green)](./LICENSE.txt)
[![Target badge](https://img.shields.io/badge/Target-i.MX_Applications_Processors-blue)](https://www.nxp.com/products/processors-and-microcontrollers/arm-processors/i-mx-applications-processors:IMX_HOME)

## GoPoint for i.MX Applications Processors

> **NOTE:** The NXP Demo Experience is now GoPoint for i.MX Applications Processors.

NXP's *GoPoint for i.MX Applications Processors* unlocks a world of possibilities. This user-friendly app launches
pre-built applications packed with the Linux BSP, giving you hands-on experience with your i.MX SoC's capabilities.
It is for the ones who are interested in showcasing various features and capabilities of the SoCs provided by NXP.
The application examples included in GoPoint are meant to be easy to run for users of all skill levels,
making complex use cases accessible to anyone. Users might need some basic knowledge when it comes to setting up
equipment on Evaluation Kits (EVKs), such as changing Device Tree Blob (DTB) files.

For more information about GoPoint, please refer to
[GoPoint for i.MX Applications Processors User's Guide](https://www.nxp.com/docs/en/user-guide/GPNTUG.pdf?_gl=1*gz87wm*_ga*ODQxOTk0OTQwLjE3MDQ5ODk3NzA.*_ga_WM5LE0KMSH*MTcwNDk4OTc2OS4xLjEuMTcwNDk4OTgyOS4wLjAuMA..).

## GoPoint for i.MX Applications Processors - Meta Layer

This repository holds all the needed configurations to build the *GoPoint for i.MX Applications Processors* package
for i.MX MPUs.

## Dependencies

* nxp-demo-experience: https://github.com/nxp-imx-support/nxp-demo-experience
* nxp-demo-experience-demos-list: https://github.com/nxp-imx-support/nxp-demo-experience-demos-list
* meta-imx: https://github.com/nxp-imx/meta-imx/

## Supported boards

i.MX Series | Product
---         | ---
i.MX 9      | i.MX 93 EVK<br>i.MX 95 EVK
i.MX 8      | i.MX 8M Mini EVK<br>i.MX 8M Nano EVK<br>i.MX 8M Plus EVK<br>i.MX 8ULP EVK<br>i.MX 8M Quad EVK<br>i.MX 8QuadMax MEK<br>i.MX 8QuadXPlus MEK
i.MX 7      | i.MX 7ULP EVKB
  
## Releases

Releases are tracked against the i.MX Linux software releases. Supported releases are listed below:

Release    | Yocto Project Version | Linux Software Release
---        | ---                   | ---
Nanbield   | 4.3                   | 6.6.3_1.0.0
Mickledore | 4.2                   | 6.1.55_2.2.0<br>6.1.36_2.1.0<br>6.1.22_2.0.0
Langdale   | 4.1                   | 6.1.1_1.0.0
Kirkstone  | 4.0                   | 5.15.71_2.2.0<br>5.15.52_2.1.0<br>5.15.32_2.0.0
Honister   | 3.4                   | 5.15.5_1.0.0
Hardknott  | 3.3                   | 5.10.72_2.2.0<br>5.10.52_2.1.0<br>5.10.35_2.0.0
Gatesgarth | 3.2                   | 5.10.9_1.0.0
Zeus       | 3.0                   | 5.4.70_2.3.1<br>5.4.70_2.3.0<br>5.4.47_2.2.0

## Install

*GoPoint for i.MX Applications Processors* is part of the i.MX Linux software releases as of *5.4.47_2.2.0*. To install,
follow the [i.MX Yocto Project User's Guide](https://www.nxp.com/docs/en/user-guide/IMX_YOCTO_PROJECT_USERS_GUIDE.pdf) for that release.

## Licensing

This repository is licensed under the [MIT](https://opensource.org/license/mit) license.