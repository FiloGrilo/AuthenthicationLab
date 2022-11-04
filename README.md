Authentication Lab repository for the 02239 Data Security course

##The project is organised as follows:

* **PrinterFacade** - provides the printer server endpoints that client can use.
* **ApplicationServer** - is a starting point for the server. It starts the server, generate printers and provide
PrinterFacade with all parameters it needs to work.
* **PasswordService** - responsible for verifying and saving the user
* **PrinterService** - responsible for all printing functions
* **UserService** - stores authenticated users list and checks if user is authenticated
* **DataSource** - for database configurations and providing data source

##Running the project
1) Run `main` method in `ApplicationServer.java` to start the server
2) Run `main` method in `Client.java` to connect to the server and send requests

##How to check results
Both server and client are printing the logs on the console


                    


