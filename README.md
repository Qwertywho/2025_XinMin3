# 2025_XinMin
**For front-end code:**\
Node_modules has been removed. After downloading the front-endfolder, run ‘npm install’ in the project directory.\
This is suitable for running in localhost. If using docker, please change this part of the code in app.js to this:
```javascript
const response = await axios.post(
        "http://backend:8080/api/coins/minCoins",
```
**For back-end code:**\
Download the coindemo_backend folder.

## To run the react-app without docker containerization (in local machine):
1)	First, in the terminal navigate to the CoinDemo_backend folder project directory. Run the Spring Boot application first (mvn spring-boot: run). The output in the terminal should indicate that the server is running on port 8080.
2)	Next, navigate to the front-end project directory, and run ‘npm start’. React development server should now be starting up. The default browser should automatically goes to http://localhost:3000.
3)	Once the application is fully loaded in the browser, you should be able to see the UI:\
 a.	Enter a target amount (e.g., 11)\
 b.	Enter coin denominations (e.g., 1,2,5)\
 c.	Click the ‘Calculate Change’ button to see the output result

## Running app using docker hub
1) You may choose to run the backend and frontend individually by building using their individual Dockerfile file
2) For test run as multi-container applications, you may run the docker-compose.yml in the root folder.
