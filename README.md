# Expense-tracker API

## 👉Summary
◼️ Project Name: Expense-tracking API <br>
◼️ I build the software which store all the transaction of the user and calculate the total expenditure with respect to day and month.<br>
◼️ It will store all the transaction of the user and give insight of whole transaction of user show that user can see all the monthly and daily expenditure.<br>
◼️ within in one click user can get whole transaction and easily to add new expenditure.<br>
◼️ We worked closely with our team of experts to execute the project successfully.<br>
◼️ GitHub link: https://lnkd.in/gxB57S9a <br>
◼️ Live link: http://3.134.178.83:8080/swagger-ui.html#/ <br>


## 👉 Requirements
✔️ IntelliJIDEA <br>
✔️ ServerPort: 8080 (use: localhost:8080) <br>
✔️ Java version: 11 <br>
✔️ Everything is present in the pom.xml (no need to download any library) <br>
✔️ Aws account to deploy project <br>
✔️ Terminus to perform deployment process <br>

## 👉Steps to run the Project
1️⃣ Download the source code and import in Intellij.<br>
2️⃣ Go to localhost:8080/ <br>
3️⃣ Put specific end_points besides the port accordingly to run the project to access and modify the data<br>
4️⃣ Download jar file of the project and push on AWS instances by using terminus<br>
5️⃣ Aws EC2 is needed to deploy project on it to get public IP address<br>
6️⃣ Terminus is where I wrote few commands and deployed project, and it helped me access the project from any place<br>

## 👉Dependencies
> Validation
* Bean Validation with Hibernate validator.

> Spring Web
* Build web, including REST-ful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
> Spring Boot DevTools
* Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
> Spring Data JPA
* Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
> Lombok
* Java annotation library which helps to reduce boilerplate code.
> Swagger
* Helps user to ease access data in HTML format
> MySQL
* MySql Database,MySql JDBC driver

## 👉Working

* This project consist of three models namely User, Expense, Status along with their corresponding dao package, controller package and service package.
* SQl-Queries has been handled with native query in the  ExpenseRepo and UserRepo as per need of the queries.
* Performed Annotation validation on all the Model class present in the MVC
* Performed Manual validation too with the need of it, as an example we cannot have more than one user with same name
* Swagger is added for the ease access of the project with link provided as follows :  http://3.15.237.47:8080/swagger-ui.html#/
* This project is deployed online with IP: 3.15.237.47 using Termius along with the help AWS-EC2 using various linus commands so that the mysql database and commands works on it
