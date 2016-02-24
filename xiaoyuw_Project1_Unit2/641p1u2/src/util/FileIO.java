/*
 * Andrew ID: xiaoyuw
 */
package util;

import java.io.*;

import exception.*;
import model.*;

/**
 * FileIO class for file input and output operation to build an object of
 * Automobile and provides methods for serialization and deserialization
 *
 */
public class FileIO {

    /**
     * Read data from a file to build an object of the Automobile
     * 
     * @param filename
     *            data source file
     * @return an object of Automobile
     * @throws AutoException
     */
    public Automobile readFile(String filename) throws AutoException {
	try {
	    File f = new File(filename);
	    BufferedReader br = new BufferedReader(new FileReader(f));
	    String line;
	    Automobile at = null;
	    int count = -1;
	    while ((line = br.readLine()) != null) {
		String[] in = line.split("\t");

		if (count == -1) {
		    try {
			if (in.length < 3)
			    throw new AutoException("MissingPrice");
			at = new Automobile(in[0], Float.parseFloat(in[1]),
				Integer.parseInt(in[2]));

		    } catch (AutoException e) {
			int errno = e.getErrno();
			System.out.println(e.getErrMsg());
			float defultprice = Float.parseFloat(e.fix(errno));
			at = new Automobile(in[0], defultprice,
				Integer.parseInt(in[1]));
		    }
		    count++;
		} else {
		    at.setOptset(count, in[0], in.length - 1);
		    for (int i = 1; i < in.length; i++) {

			String[] value = in[i].split(",");
			try {
			    if (value.length < 2)
				throw new AutoException("MissingPrice");
			    at.setOpt(in[0], i - 1, value[0],
				    Float.parseFloat(value[1]));
			} catch (AutoException e) {
			    int errno = e.getErrno();
			    System.out.println(e.getErrMsg());
			    float defultprice = Float.parseFloat(e.fix(errno));
			    at.setOpt(in[0], i - 1, value[0], defultprice);
			}
		    }
		    count++;
		}

	    }

	    br.close();
	    return at;

	} catch (FileNotFoundException e) {
	    throw new AutoException("InvalidFileName");
	} catch (Exception e) {

	    throw new AutoException("FileFormatError");
	}

    }

    /**
     * Serialize an object
     * 
     * @param auto
     *            object to be serialized
     * @throws Exception
     */
    public void serializeAuto(Automobile auto) throws Exception {
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
    public Automobile deserializeAuto(String file) throws Exception {
	ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
	Automobile auto = (Automobile) in.readObject();
	in.close();
	return auto;
    }

}
