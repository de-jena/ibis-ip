# Build LXC for ibis ip depoyment on tram and bus routers


## Prerequests 

Install lxc and utils
```
apt install lxc lxc-utils
```

copy the LXC template from templates/lxc-ibis to /usr/share/lxc/templates/

## Build 

run the createLXC script as root 

## Customizing network

To change the static IP of the LXC edit the netplan configuration in /usr/share/lxc/templates/lxc-ibis (line 100)
