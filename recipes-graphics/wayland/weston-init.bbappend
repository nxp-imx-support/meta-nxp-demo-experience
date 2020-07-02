do_install_append_mx8(){
    install -d -m 755 ${D}/home/root/.config
    cp ${D}${sysconfdir}/xdg/weston/weston.ini ${D}/home/root/.config
    echo "\n[launcher]\nicon=/home/root/.nxp-demo-experience/icon/icon_demo_launcher.png\npath=/usr/bin/demoexperience\n\n[launcher]\nicon=/usr/share/weston/terminal.png\npath=/usr/bin/weston-terminal" >> ${D}/home/root/.config/weston.ini
}

do_install_append_mx7ulp(){
    install -d -m 755 ${D}/home/root/.config
    cp ${D}${sysconfdir}/xdg/weston/weston.ini ${D}/home/root/.config
    echo "\n[launcher]\nicon=/home/root/.nxp-demo-experience/icon/icon_demo_launcher.png\npath=/usr/bin/demoexperience\n\n[launcher]\nicon=/usr/share/weston/terminal.png\npath=/usr/bin/weston-terminal" >> ${D}/home/root/.config/weston.ini
}

FILES_${PN}_append_mx8 = " /home/root/.config/*"
FILES_${PN}_append_m7ulp = " /home/root/.config/*"
