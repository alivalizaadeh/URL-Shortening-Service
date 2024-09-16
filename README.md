## 🎯 Overview

A lightweight standalone url shortening application implemented in Java. This tool allows users to create the shortest
url ,updating, deleting, and viewing number of access, with data stored in a No-SQL database (MongoDB) for persistence

**Note:** This project is a sample solution for
the [URL Shortening Service](https://roadmap.sh/projects/url-shortening-service) challenge
from the [roadmap.sh](https://roadmap.sh/)

## ✨ Features

- **Create a new short URL:** Create new shortest code for link.
- **Retrieve an original URL from a short URL:** Can retrieve an original url and data from the shortest code.
- **Update an existing short URL:** Can update original url by the shortest code.
- **Delete an existing short URL:** Can delete original url by the shortest code.
- **Get statistics on the short URL:** Can retrieve the access number count by the shortest code.

## 📃 Requirements

1. JDK version 17, you must [Download](https://www.oracle.com/java/technologies/downloads/#java17) it for your platform
   and install.
2. Maven, you must [Download](https://maven.apache.org/download.cgi) it (Version 3), if need you
   can [visit medium article](https://medium.com/@gauravshah97/how-to-install-maven-on-windows-39ff317e40cf) to learn
   how set variable on your device environment.
3. MongoDB, you must [Download](https://www.mongodb.com/try/download/community) MongoDB and start their service on your
   device.(if you are a Windows user, I recommend to
   install [GUI version](https://fastdl.mongodb.org/windows/mongodb-windows-x86_64-7.0.14-signed.msi))

## 🚀 How to Run

1. Clone the repository
    ```bash
    git clone https://github.com/alivlizadeh1/URL-Shortening-Service.git
    cd Task-Tracker
    git checkout nosql
    ```
2. Configure application.yml file

   ``
   Create a database and change <database-name> on
   application.yml to your database name created.
   ``


3. Compile
    ```bash
    mvn clean package
    ```
4. Run
    ```bash
    mvn spring-boot:run
    ```

## 📘 Usage Example

1. **Create new shortest code :** 
   ``
   You must set the url in body of request, for example :
   ``
```bash
curl -i -X POST -H "Content-Type: application/json" -d "{\"url\":\"github.com\"}" http://localhost:8080/shorten
```
2. **Get original url by shortest code :**
     ``
     You must set the existed shortest code in below command:
     ``
```bash
## change '0ziy2' and set real data
curl -i -X GET -H "Content-Type: application/json" http://localhost:8080/shorten/0ziy2
```
3. **Update original url by shortest code :**
   ``
   You must set the existed shortest code in url and also field 'url' in below command :
   ``
```bash
## change '0ziy2' and set real data and also set another variable in body 'url' (youtube.com)
curl -i -X PUT -H "Content-Type: application/json" -d "{\"url\":\"youtube.com\"}" http://localhost:8080/shorten/0ziy2
```
4. **Get statistic(Number of Access) by shortest code :**
   ``
   You must set the existed shortest code in below command :
   ``
```bash
## change '0ziy2' and set real data
curl -i -X GET -H "Content-Type: application/json" http://localhost:8080/shorten/0ziy2/stats
```
5. **Delete an original url by shortest code :**
   ``
   You must set the existed shortest code in below command :
   ``
```bash
## change '0ziy2' and set real data
curl -i -X DELETE -H "Content-Type: application/json" http://localhost:8080/shorten/0ziy2
```