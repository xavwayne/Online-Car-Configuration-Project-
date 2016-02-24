641p1u4client is the project folder for the project 1 unit 4 client.
641p1u4client/src/adapter package contains :
   BuildAuto.java : Connect  interfaces and implementations
   CreateAuto.java :interface -- create an automobile from datafile and print it
   UpdateAuto.java : interface -- update optionset name and option price
   ProxyAutomobile.java : abstract class -- specific implementation of the methods declared in the interfaces.
641p1u4client/src/exception package contains:
   AutoException.java : customer exception handler
   Fix1to100.java : delegate class to fix exceptions raised from model package
   FixAuto.java : interface declare fix method
641p1u4client\src\model package contains:
   Automobile.java : Automotive class is the model of a car. It has name and base price, contains a set of option set. It also provides some methods to set, update and delete option sets and options. The CRUD methods in this class should be synchronized, because an automobile object is shared by multiple threads. Modifying shared data in multiple threads can cause data corruption.
   Fleet.java : contains a LHM of automobiles
   OptionSet.java : Optionset class definition, contains a number of options.
641p1u4client\src\util package contains:
   FileIO.java FileIO class for file input and output operation to build an object of Automobile and provides methods for serialization and deserialization
641p1u4client\src\driver package contains:
   Driver.java :  Driver class-- start a client
641p1u4client\src\scale package contains:
   EditOption.java: An application thread definition. When it runs, it either edits option set name or edit option price.
   Scalable.java:  An interface that expose the methods in ProxyAutomobile to edit option set name or option price in a thread
641p1u4client\src\client package contains:
   CarModelOptionsIO.java:  Upload a properties file from client to server and build an automobile at server side, receiving a verification of success.
   DefaultSocketClient.java: Start a client-server connection on its own thread.
   SelectCarOption.java: Show available models on server, select a model and configure that model at client side.
   SocketClientConstants.java: Constants for socket connection.
   SocketClientInterface.java: Interface -- provide methods to establish connection, handle requests and close connection.

641p1u4client/Ford.txt, Toyota.properties are  data files of  automobile models.

641p1u4server is the project folder for the project 1 unit 4 client.
641p1u4server/src/adapter package contains :
   BuildAuto.java : Connect  interfaces and implementations
   CreateAuto.java :interface -- create an automobile from datafile and print it
   UpdateAuto.java : interface -- update optionset name and option price
   ProxyAutomobile.java : abstract class -- specific implementation of the methods declared in the interfaces.
641p1u4server/src/exception package contains:
   AutoException.java : customer exception handler
   Fix1to100.java : delegate class to fix exceptions raised from model package
   FixAuto.java : interface declare fix method
641p1u4server\src\model package contains:
   Automobile.java : Automotive class is the model of a car. It has name and base price, contains a set of option set. It also provides some methods to set, update and delete option sets and options. The CRUD methods in this class should be synchronized, because an automobile object is shared by multiple threads. Modifying shared data in multiple threads can cause data corruption.
   Fleet.java : contains a LHM of automobiles
   OptionSet.java : Optionset class definition, contains a number of options.
641p1u4server\src\util package contains:
   FileIO.java FileIO class for file input and output operation to build an object of Automobile and provides methods for serialization and deserialization
641p1u4server\src\driver package contains:
   Driver.java :  Driver class-- start a server
641p1u4server\src\scale package contains:
   EditOption.java: An application thread definition. When it runs, it either edits option set name or edit option price.
   Scalable.java:  An interface that expose the methods in ProxyAutomobile to edit option set name or option price in a thread.
641p1u4sever\src\server package contains:
   AutoServer.java: Interface for server to build an automobile, add it to the LHM, show available models on server and send a model to client.
   BuildCarModelOptions.java:  Act like a server, taking requests from clients and serving to clients.
   DefaultSocketClient.java: serve the request from clients on its on thread.
   SocketClientConstants.java: Constants for socket connection.
   SocketClientInterface.java: Interface -- provide methods to establish connection, handle requests and close connection



P1U4.pdf is the class diagram containing server and client.


test_output.txt is the output of simulation.
  It shows the process of transfering properties file from client to server, creating automobile and adding it to the LHM at server, selecting a model and configureing it.  