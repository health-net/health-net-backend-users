#!/bin/bash

echo "[WARNING] The data stored in health_net_staff database (if present) will be deleted."

read -r -p "Enter username: " MYSQL_USERNAME
mysql -u "$MYSQL_USERNAME" -p <<QUERIES
DROP DATABASE IF EXISTS health_net_staff;

CREATE DATABASE IF NOT EXISTS health_net_staff;

USE health_net_staff;

CREATE TABLE IF NOT EXISTS staff (
    id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(500)
);
QUERIES
echo "Operation performed successfully"