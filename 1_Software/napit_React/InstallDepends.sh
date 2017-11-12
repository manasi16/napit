#install node 7 and npm 4.2 
#See: https://linuxconfig.org/how-to-install-node-js-on-ubuntu-16-04-xenial-xerus-linux-server

curl -sL https://deb.nodesource.com/setup_7.x | sudo -E bash -
sudo apt-get install nodejs

#run
sudo sysctl -w fs.inotify.max_user_instances=1024
sudo sysctl -w fs.inotify.max_user_watches=12288
