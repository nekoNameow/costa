# Costa🚗🚗🚗
GraphQL serving application shows vehicles

### 🚗Costa Application Server
#### Installation
The project builds on SpringBoot and Maven. Clone the project and import it into the local env, then the IDE is supposed to automatically finish the configure. Java 19 is used in the back end. 
#### Run
Click "Run CostaApplication.java" (\src\main\java\com\hailing\costa\CostaApplication.java) to start the back end server.

### 🚗Costa Application Client
#### Installation
The front-end use Nodejs18 + React18 + Typescript.
#### Run
The project entry file is located at \src\index.tsx. To start the application, please run the command in the root directory:
```bash
npm start
```
-----------

### 🚗 Enjoy ;)
If everything goes well, now this page will show at http://localhost:3000/vehicle/list as below:  
![LoginAsTest](https://user-images.githubusercontent.com/124257897/216813506-ab0855be-355c-49f3-821d-aaa16ca5506c.png)
Please click the login button at the top right corner to view more vehicles!  
✔️The login function is used for testing the status code 401.  
✔️React router, typescript, and MongoDB are used.  
✔️Some performance features:  
The schedule for fetching data was stated as 1 hour. The cache is used for quicker response.  
If the page does not reload, the data is read from the status management instead of the HTTP request due to performance considerations. For the same reason, only router providers will update when the page router is changed instead of rendering the whole page.

Tips:  
MockAPi need configure and use Mockoon. The back-end get provided data from VGCS-COSTA-assignment-mock.json.  
If PNPM has not been installed, please run the following command to install it at the root directory for starting the application:  
```bash
npm install pnpm -g
pnpm install
```
