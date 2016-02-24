
641P1U6 is the project folder for the project 1 unit 5 server. 
641P1U6/src/adapter package contains :
   BuildAuto.java : Connect  interfaces and implementations
   CreateAuto.java :interface -- create an automobile from datafile and print it
   UpdateAuto.java : interface -- update optionset name and option price
   ProxyAutomobile.java : abstract class -- specific implementation of the methods declared in the interfaces.
641P1U6/src/exception package contains:
   AutoException.java : customer exception handler
   Fix1to100.java : delegate class to fix exceptions raised from model package
   FixAuto.java : interface declare fix method
641P1U6\src\model package contains:
   Automobile.java : Automotive class is the model of a car. It has name and base price, contains a set of option set. It also provides some methods to set, update and delete option sets and options. The CRUD methods in this class should be synchronized, because an automobile object is shared by multiple threads. Modifying shared data in multiple threads can cause data corruption.
   Fleet.java : contains a LHM of automobiles
   OptionSet.java : Optionset class definition, contains a number of options.
641P1U6\src\util package contains:
   FileIO.java FileIO class for file input and output operation to build an object of Automobile and provides methods for serialization and deserialization
641P1U6\src\driver package contains:
   Driver.java :  Driver class-- start a server
641P1U6\src\scale package contains:
   EditOption.java: An application thread definition. When it runs, it either edits option set name or edit option price.
   Scalable.java:  An interface that expose the methods in ProxyAutomobile to edit option set name or option price in a thread.
641P1U6\src\server package contains:
   AutoServer.java: Interface for server to build an automobile, add it to the LHM, show available models on server and send a model to client.
   BuildCarModelOptions.java:  Act like a server, taking requests from clients and serving to clients.
   DefaultSocketClient.java: serve the request from clients on its on thread.
   SocketClientConstants.java: Constants for socket connection.
   SocketClientInterface.java: Interface -- provide methods to establish connection, handle requests and close connection
641P1U6\src\jdbc package contains:
   Create.java: Create-- Create a database or insert data in the database.
   Delete.java: Delete -- delete an automobile from the database.
   Info.java: Info -- show the data in a table.
   JDBCConstants.java: Constants for JDBC.
   Update.java: update data in the database.

641P1U6/FordZTW.txt, Toyota.properties are  data files of  automobile models to build on server. When reading files, filetype should be specified.   sql_command.txt is the file containing SQL.

P1U6.pdf is the class diagram containing server and client.

test_output.txt is the output of tests.
  It shows the process of creating tables, inserting data of automobiles in the database, updating information in the database and deleting an automobile in the database.  

Lessons Learned.docx is the document about the lessons learned from the mini 1.

Reclaims file folder contains the units to be reclaimed from unit 1 to unit 4. Each folder contains separate change log.