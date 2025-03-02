# 🚀 **DevOps CI/CD Pipeline with Jenkins, Docker, SonarQube, and Snyk**
> A **fully automated CI/CD pipeline** implementing DevOps best practices for **building, testing, securing, and deploying** applications.

![Jenkins](https://img.shields.io/badge/Jenkins-Automation-blue?style=for-the-badge&logo=jenkins)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue?style=for-the-badge&logo=docker)
![SonarQube](https://img.shields.io/badge/SonarQube-Code%20Quality-blue?style=for-the-badge&logo=sonarqube)
![Snyk](https://img.shields.io/badge/Snyk-Security-red?style=for-the-badge&logo=snyk)
![Nexus](https://img.shields.io/badge/Nexus-Artifact%20Management-orange?style=for-the-badge&logo=apache-nexus)

---

## **🌟 Key Features**
✅ **Automated Build & Test** → Maven-based build with unit tests using **Mockito**  
✅ **Static Code Analysis** → **SonarQube** ensures code quality and reliability  
✅ **Security Scanning** → **Snyk** checks for vulnerabilities in dependencies  
✅ **Artifact Management** → Deployed to **Nexus** repository  
✅ **Containerized Deployment** → Dockerized application with **Docker Compose**  
✅ **Database Integration** → Uses **MySQL** as the backend  

---

## **🛠️ Tech Stack**
- 🏢 **CI/CD** → **Jenkins, GitHub Actions**
- 🛋 **Containerization** → **Docker, Docker Compose**
- 📊 **Code Quality** → **SonarQube**
- 🔒 **Security Analysis** → **Snyk**
- 📂 **Artifact Management** → **Nexus Repository**
- 💾 **Database** → **MySQL**
- 🔄 **Scripting & Automation** → **Bash, Ansible**
- 🛠️ **Build Tools** → **Maven**

---

## **🚀 How This Project Works**
This **fully automated DevOps pipeline** is structured as follows:

### **1️⃣ CI/CD Workflow**
✅ **Code Checkout** → Pulls code from **GitHub**  
✅ **Build & Test** → **Maven** builds the project & runs tests  
✅ **Code Analysis** → **SonarQube** checks code quality  
✅ **Security Scan** → **Snyk** detects vulnerabilities  
✅ **Artifact Deployment** → Builds and pushes artifacts to **Nexus**  
✅ **Docker Image Build** → Creates a **Docker image**  
✅ **Deployment with Docker Compose** → Runs the app & **MySQL database**  

---

## **📆 Setup & Installation**
### **🔹 1. Clone the Repository**
```sh
git clone https://github.com/ghassenjebari/Devops.git
cd Devops
```

### **🔹 2. Run Jenkins Locally**
Ensure **Jenkins** is installed with:
- **Pipeline**
- **SonarQube Scanner**
- **Docker Pipeline**
- **Snyk Security Plugin**

Run Jenkins:
```sh
docker run -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts
```

### **🔹 3. Run SonarQube**
```sh
docker run -d --name sonar -p 9000:9000 sonarqube:lts
```

### **🔹 4. Run Nexus**
```sh
docker run -d -p 8081:8081 --name nexus sonatype/nexus3
```

### **🔹 5. Start the Pipeline in Jenkins**
- Add **SNYK_TOKEN** as a Jenkins credential  
- Configure **SonarQube Server** in Jenkins settings  
- Run the **Jenkins pipeline**  

---

## **📆 Deploying with Docker Compose**
```sh
docker-compose up -d
```
This will:
✅ Start a **MySQL** database  
✅ Deploy the **Spring Boot application** inside a **Docker container**  

---

## **💡 Why This Project?**
This **end-to-end DevOps pipeline** replicates **real-world production environments**, demonstrating:
✅ **Industry-standard CI/CD best practices**  
✅ **Security-focused software development**  
✅ **Automated deployment with Docker & Kubernetes**  

---

## **👨‍💻 About Me**
**👤 Ghassen Jebbari**  
📧 [ghassen.jebbari@gmail.com](mailto:ghassen.jebbari@gmail.com)  
🔗 [GitHub](https://github.com/ghassenjebari) | [LinkedIn](https://www.linkedin.com/in/jebbari-ghassen-363bab195/)  
🌐 [Portfolio Website](https://ghassen-jebbari.de/)  

---

