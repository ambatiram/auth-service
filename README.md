## AuthService Server

### Live Demo

	The application is hosted on Heroku free tier. Check out the live demo REST API end point at https://auth-service-ambatiram.herokuapp.com/swagger-ui.html

## Steps to Setup AuthService Server (auth-service)

1. **Clone the application**

	```bash
	git clone https://github.com/ambatiram/auth-service.git
	cd auth-service
	```
2. **Create MySQL database**

	```bash
	create database auth_service
	```
	Import schema from file available auth_service in root directory
	
3. **Change MySQL username and password as per your MySQL installation**

	+ open `src/main/resources/application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation

4. **Run the app**

	You can run the spring boot app by typing the following command -

	```bash
	mvn spring-boot:run

	The server will start on port 5000. The spring boot app includes the front end build also, 	so you'll be able to access the complete application on `http://localhost:5000`.

	You can also package the application in the form of a `jar` file and then run it like so -

	```bash
	mvn package
	java -jar target/auth-service-0.0.1-SNAPSHOT.jar

5. **End points documention**
	
	You can get all the end points through swagger UI URL as http://localhost:8080/swagger-ui.html
	

