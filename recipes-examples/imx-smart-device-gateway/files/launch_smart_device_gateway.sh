#!/bin/bash

# Copyright 2026 NXP
# SPDX-License-Identifier: BSD-3-Clause 

export DISPLAY=:0

# Stop the connector service first
systemctl stop eiq-aaf-connector

xterm \
  -bg "#0000CD" \
  -fg "#FFD700" \
  -cr "#FFFF00" \
  -geometry 180x60 \
  -title "Smart Device Gateway" \
  -e bash -c "
    echo '===================================================================';
    echo '    Configuring Smart Device Gateway packages, please wait...';
    echo '===================================================================';
    echo 'Remember to set date/time for proper models download.';
    echo ''; date; echo '';
    sleep 5
    /usr/share/smart-device-gateway/install.sh
    echo 'Configuration completed, now, launching the server...';
    sleep 5
    reset
    echo '=================================================='; \
    echo '     Welcome to Smart Device Gateway server'; \
    echo '=================================================='; \
    echo ''; echo 'The server uses port 8080, please validate local IP address for client connection:'; \
    echo ''; ifconfig; echo ''; echo 'Starting application...'; \
    echo ''; run_server --host 0.0.0.0 --port 8080
    "
