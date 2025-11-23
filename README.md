# 🏦 Banking Application with Zabbix Monitoring 🚀

## 📋 Project Overview
### A 3-tier Spring Boot banking application 💰 with MySQL database, fully containerized using Docker 🐳 and monitored with Zabbix 📊

## 🏗️ Architecture
* 🌐 Frontend: Spring Boot Web Application (Port 8080)

* ⚙️ Backend: Spring Boot REST API with JPA/Hibernate

* 🗄️ Database: MySQL 8.0 (Port 3306)

* 📈 Monitoring: Zabbix Stack (Port 8081)

* 📦 Containers: Docker with Docker Compose

## ⚡ Prerequisites
- ☕ Java 17+

- 🐳 Docker & Docker Compose

- 📦 Maven

## 🚀 Quick Start
### 1. Clone and Build 🔧
```
git clone <repository>
cd 3-tier-banking-app
./mvnw clean package -DskipTests
```

## 2. Start Application with Monitoring 🎯

### Start Zabbix monitoring stack
```
docker-compose -f zabbix-docker-compose.yml up -d

# Wait 2 minutes for Zabbix initialization ⏳
sleep 120

# Start banking application with monitoring
docker-compose up -d
```

## 3. Access Applications 🌍
### 🏦 Banking App: http://localhost:8080

### 📊 Zabbix Monitoring: http://localhost:8081

- 👤 Username: Admin

- 🔑 Password: zabbix

## Zabbix Host Configuration 🎯
- 🏷️ Hostname: bankapp-server

- 📡 Agent Interface: IP 172.20.0.3, Port 10050

### 📋 Templates Applied:

- 📟 Template OS Linux by Zabbix agent

- 🗄️ Template App MySQL by Zabbix agent

## ✨ Key Features Implemented
### 🎯 Application Features 🌟
- ✅ Spring Boot 3.x with Spring Security 🔒

- ✅ JPA/Hibernate with MySQL 🗄️

- ✅ RESTful API endpoints 🔗

- ✅ Thymeleaf templates for web interface 🌐

- ✅ Spring Boot Actuator for monitoring endpoints 📊

## 📈 Monitoring Features 👁️
- ✅ Application Health: /actuator/health monitoring 🏥

- ✅ Database Monitoring: Connection pool, query performance 📈

- ✅ Container Monitoring: Docker container status and resource usage 🐳

- ✅ System Metrics: CPU, memory, disk, network monitoring 💻

- ✅ Custom Business Metrics: Transaction counts, user sessions 💰

## 🐳 Docker Features 📦
- ✅ Multi-stage builds for optimized images 🏗️

- ✅ Docker Compose for orchestration 🎼

- ✅ Named volumes for data persistence 💾

- ✅ Custom networks for service isolation 🌐

## 🛠️ Troubleshooting Guide 🔧
#### 🔍 Common Issues and Solutions
### Database Connection Issues 🗄️

### Test MySQL connectivity
```
docker exec mysql-bank mysql -u root -pgreekgod -e "SELECT 1;"

# Check if tables were created
docker exec mysql-bank mysql -u root -pgreekgod -e "USE bankappdb; SHOW TABLES;"
```

### Application Not Starting ❌

#### Check application logs
```
docker logs bankapp-container

# Verify environment variables
docker exec bankapp-container env | grep SPRING
```

### Zabbix Agent Not Available 📡

#### Test agent connectivity
```
docker exec zabbix-server zabbix_get -s zabbix-agent-bankapp -k "system.uptime"

# Check agent logs
docker logs zabbix-agent-bankapp
```

#### No Data in Zabbix 📊
- 🔍 Verify host interface configuration (IP: 172.20.0.3, Port: 10050)

- 📋 Check if templates are properly linked

- ⏳ Wait 5-10 minutes for initial data collection

## 📊 Monitoring Dashboard 📈
### After setup, you can view monitoring data in Zabbix:

- 📈 Latest Data: Monitoring → Latest Data → Select bankapp-server

- 🎯 Dashboards: Create custom dashboards for your banking metrics

- ⚠️ Problems: Monitor for alerts and issues

- 📉 Graphs: Visualize performance trends

- 📈 Performance Metrics Monitored
- 🎯 Application Metrics 🌟
- 🌐 HTTP request count and response times

- ☕ JVM memory usage and garbage collection

- 🔗 Database connection pool status

- 💰 Custom business transactions

## 💻 System Metrics 🖥️
- ⚡ CPU utilization per core

- 🧠 Memory usage and swap

- 💾 Disk I/O and space utilization

- 🌐 Network traffic and connections

## 🗄️ Database Metrics 📊
- 🔗 Active connections

- ⚡ Query performance

- 🎯 Buffer pool efficiency

- ⏳ Table locks and slow queries

## 🔒 Security Notes 🛡️
- ⚠️ MySQL root password is set in environment variables

- ✅ Spring Security provides application-level security 🔒

- ⚠️ Zabbix uses default credentials (change in production) 🔑

- ✅ Docker containers run with minimal privileges 🔐

## 🏭 Production Considerations 🚀
- 🔐 Use secrets management for passwords

- 🔒 Implement SSL/TLS for database connections

- 👥 Configure Zabbix user authentication

- 💾 Set up automated backups

- 📝 Implement log rotation and monitoring

## 🔄 Maintenance 🛠️
- 🔄 Regular Docker image updates

- 🗄️ MySQL database optimization

- 📊 Zabbix template updates

- 🔒 Security patch management

## ❓ Support 🤝
#### For issues with this setup, check:

- 🐳 Docker container status and logs

- 📊 Zabbix agent connectivity

- 🗄️ Database connection parameters

- 🌐 Network configuration between containers

*📝 Note:  This README provides a comprehensive guide to setting up, running, and maintaining the banking application with full monitoring capabilities using Zabbix.*

## ⭐ Happy Banking! ⭐