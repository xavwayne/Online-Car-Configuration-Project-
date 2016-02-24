641p1u2 is the project folder for the project 1 unit 2.
641p1u2/src/adapter package contains :
   BuildAuto.java : Connect  interfaces and implementations
   CreateAuto.java :interface -- create an automobile from datafile and print it
   UpdateAuto.java : interface -- update optionset name and option price
   ProxyAutomobile.java : abstract class -- specific implementation of the methods declared in the interfaces above.
641/src/exception package contains:
   AutoException.java : customer exception handler
   Fix1to100.java : delegate class to fix exceptions raised from model package
   FixAuto.java : interface declare fix method
641p1u2\src\model package contains:
   Automobile.java : Automotive class is the model of a car. It has name and base price, contains a set of option set. It also provides some methods to set, update and delete option sets and options.
   Fleet.java : contains a LHM of automobiles
   OptionSet.java : Optionset class definition, contains a number of options.
641p1u2\src\util package contains:
   FileIO.java FileIO class for file input and output operation to build an object of Automobile and provides methods for serialization and deserialization
641p1u2\src\driver package contains:
   Driver.java :  Driver class-- test the methods in interfaces including CreateAuto, UpdateAuto. Additional test for recording option choice.

641p1u2/FordZTW.txt and Porsche.txt are two different data files for two different automobile model

P1U2.pdf is the class diagram.

test_output.txt is the output of tests in Driver class.