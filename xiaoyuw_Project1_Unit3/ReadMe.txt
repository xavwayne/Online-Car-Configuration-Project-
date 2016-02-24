641p1u3 is the project folder for the project 1 unit 3.
641p1u3/src/adapter package contains :
   BuildAuto.java : Connect  interfaces and implementations
   CreateAuto.java :interface -- create an automobile from datafile and print it
   UpdateAuto.java : interface -- update optionset name and option price
   ProxyAutomobile.java : abstract class -- specific implementation of the methods declared in the interfaces.
641p1u3/src/exception package contains:
   AutoException.java : customer exception handler
   Fix1to100.java : delegate class to fix exceptions raised from model package
   FixAuto.java : interface declare fix method
641p1u3\src\model package contains:
   Automobile.java : Automotive class is the model of a car. It has name and base price, contains a set of option set. It also provides some methods to set, update and delete option sets and options. The CRUD methods in this class should be synchronized, because an automobile object is shared by multiple threads. Modifying shared data in multiple threads can cause data corruption.
   Fleet.java : contains a LHM of automobiles
   OptionSet.java : Optionset class definition, contains a number of options.
641p1u3\src\util package contains:
   FileIO.java FileIO class for file input and output operation to build an object of Automobile and provides methods for serialization and deserialization
641p1u3\src\driver package contains:
   Driver.java :  Driver class-- test multithreading and synchronization for editing optionset and option. The test modifies the Transmission/automatic 's price from 0 to 100, and then to 50. Then modifies the name of Transmission to Gear and then to Shift. So, if the threads operate with synchronization, the output should be "Shift--50", otherwise, the output is unpredictable and much likely to throw exceptions.
641p1u3\src\scale package contains:
   EditOption.java: An application thread definition. When it runs, it either edits option set name or edit option price.
   Scalable.java:  An interface that expose the methods in ProxyAutomobile to edit option set name or option price in a thread


641p1u3/FordZTW.txt is a  data file of an automobile model.

P1U3.pdf is the class diagram.

test_output.txt is the output of tests in Driver class.
  When the methods in Automobile is synchronized, the output is correct.
  When the methods in Automobile is not synchronized, the output is unpredictable because of data corruption.