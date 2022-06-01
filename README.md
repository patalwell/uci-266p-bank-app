# uci-266p-bank-app

This repository contains a sample Java SpringBoot web application that mimics a banking
application. It contains several vulnerabiltiies and is only intended to be used in a
sandbox environment for educational purposes.

WARNING - DO NOT USE FOR A PRODUCTION SETTING! THIS APP CONTAINS VULNERABILITIES. IT IS USED AS A MEANS TO UNDERSTANDING SECURITY BEST PRACTICES.

### Requirements
- Java Version 11
- Apache Maven 3.8.4

### Run Time Instructions

1. Make sure you're using Java Version 11 and Maven 3.8.4 by running java -version and mvn -version from the command line.

```
➜  uci-266p-bank-app-1.0.1 java -version
openjdk version "11.0.14.1" 2022-02-08 LTS
OpenJDK Runtime Environment Corretto-11.0.14.10.1 (build 11.0.14.1+10-LTS)
OpenJDK 64-Bit Server VM Corretto-11.0.14.10.1 (build 11.0.14.1+10-LTS, mixed mode)

➜  uci-266p-bank-app-1.0.1 mvn -version
Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
Maven home: /opt/apache-maven-3.8.4
Java version: 11.0.14.1, vendor: Amazon.com Inc., runtime: /Users/palwell/Library/Java/JavaVirtualMachines/corretto-11.0.14.1/Contents/Home
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.7", arch: "x86_64", family: "mac"
```

3. Clone this repository: `git clone https://github.com/patalwell/uci-266p-bank-app.git` or download the latest artifact.
4. Navigate to the parent project directory: `cd uci-266p-bank-app/`
5. Run `mvn clean install`. This will create a JAR in the target/ directory of the project.
6. Run the application with `java -jar target/app-0.0.1-SNAPSHOT.jar `
7. Open a browser and navigate to `http://localhost:8080/`
8. You should be prompted with a login page.

![login-page](docs/img/login_page.png)

### To Do
- [x] Introduce 4 Vulnerabilities
- [x] Write Report On Secure Code and Vulnerabilities
- [x] Create a static release for use https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository
