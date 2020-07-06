ROOTFS_POSTPROCESS_COMMAND_append_mx8 = "install_demo; "
ROOTFS_POSTPROCESS_COMMAND_append_mx7ulp = "install_demo; "



install_demo() {
    echo "\n[launcher]\nicon=/home/root/.nxp-demo-experience/icon/icon_demo_launcher.png\npath=/usr/bin/demoexperience\n\n[launcher]\nicon=/usr/share/weston/terminal.png\npath=/usr/bin/weston-terminal" >> ${IMAGE_ROOTFS}${sysconfdir}/xdg/weston/weston.ini
}
