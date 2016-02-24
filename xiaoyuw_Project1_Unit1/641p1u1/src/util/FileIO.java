/*
 * Andrew ID: xiaoyuw
 */
package util;

import java.io.*;
import model.*;

/**
 * FileIO class for file input and output operation to build an object of
 * Automotive and provides methods for serialization and deserialization
 *
 */
public class FileIO {

    /**
     * Read data from a file to build an object of the Automotive
     * 
     * @param filename
     *            data source file
     * @return an object of Automotive
     * @throws Exception
     */
    public Automotive readFile(String filename) throws Exception {
	File f = new File(filename);
	BufferedReader br = new BufferedReader(new FileReader(f));
	String line;
	Automotive at = null;
	int count = -1;
	while ((line = br.readLine()) != null) {
	    String[] in = line.split("\t");

	    if (count == -1) {
		at = new Automotive(in[0], Float.parseFloat(in[1]),
			Integer.parseInt(in[2]));
		count++;
	    } else {
		at.setOptset(count, in[0], in.length - 1);
		for (int i = 1; i < in.length; i++) {

		    String[] value = in[i].split(",");
		    at.setOpt(in[0], i - 1, value[0],
			    Float.parseFloat(value[1]));
		}
		count++;
	    }

	}
	br.close();
	return at;

    }

    /**
     * Serialize an object
     * 
     * @param auto
     *            object to be serialized
     * @throws Exception
     */
    public void serializeAuto(Automotive auto) throws Exception {
	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
		"auto.ser"));
	out.writeObject(auto);
	out.close();
    }

    /**
     * Deserialize from a file
     * 
     * @param file
     *            source file
     * @return a deserialized object
     * @throws Exception
     */
    public Automotive deserializeAuto(String file) throws Exception {
	ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
	Automotive auto = (Automotive) in.readObject();
	in.close();
	return auto;
    }

}
