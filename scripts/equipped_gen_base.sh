#!/bin/bash

sudo pacman -S --noconfirm mysql
sudo mysql_install_db --user=mysql --basedir=/usr --datadir=/var/lib/mysql

sudo systemctl start mysqld
sudo systemctl enable mysqld
sudo systemctl status mysqld


#sudo mysql -u root

#create database spring_social;
#CREATE USER 'kamran'@'localhost' IDENTIFIED BY 'password';
#GRANT ALL ON *.* TO 'kamran'@'localhost';
