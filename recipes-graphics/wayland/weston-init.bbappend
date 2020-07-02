do_install_append(){
    install -d -m 755 ${D}/home/root/.config
    cp ${D}${sysconfdir}/xdg/weston/weston.ini ${D}/home/root/.config
    echo "\n[launcher]\nicon=/home/root/.nxp-demo-experience/icon/icon_demo_launcher.png\npath=/usr/bin/demoexperience\n\n[launcher]\nicon=/usr/share/weston/terminal.png\npath=/usr/bin/weston-terminal" >> ${D}/home/root/.config/weston.ini
}

FILES_${PN} += "/home/root/.config/* "
