/*
 * Andrew ID: xiaoyuw
 */
package util;

import java.io.*;
import java.net.*;
import java.util.*;

import exception.*;
import model.*;

/**
 * FileIO class for file input and output operation to build an object of
 * Automobile and provides methods for serialization and deserialization
 *
 */
public class FileIO {

    /**
     * Read data from a txt file to build an object of the Automobile
     * 
     * @param filename
     *            data source file
     * @return an object of Automobile
     * @throws AutoException
     */
    private Automobile readFile(String filename) throws AutoException {
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
			if (in.length < 2)
			    throw new AutoException("MissingPrice");
			at = new Automobile(in[0], Float.parseFloat(in[1]));

		    } catch (AutoException e) {
			int errno = e.getErrno();
			System.out.println(e.getErrMsg());
			e.log();
			float defultprice = Float.parseFloat(e.fix(errno));
			at = new Automobile(in[0], defultprice);
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
			    e.log();
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
	    e.printStackTrace();

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

    /**
     * load a file to a Properties object at client end
     * 
     * @param filename
     *            file name
     * @return a Properties object
     * @throws AutoException
     */
    public Properties loadFile(String filename) throws AutoException {

	Properties props = new Properties();
	try {
	    FileInputStream in = new FileInputStream(filename);
	    props.load(in);
	    return props;

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new AutoException("InvalidFilename");
	}

    }

    /**
     * resovle a Properties object at server end
     * 
     * @param prop
     * @return
     * @throws AutoException
     * @throws NumberFormatException
     */
    public Automobile readObj(Properties prop) throws AutoException {

	Automobile at;
	String carmake = prop.getProperty("CarMake");
	if (carmake != null) {

	    String carmodel = prop.getProperty("CarModel");
	    String carname = carmake + " " + carmodel;
	    String baseprice = prop.getProperty("BasePrice");
	    if (baseprice != null)
		at = new Automobile(carname, Float.parseFloat(baseprice));
	    else
		at = new Automobile(carname);

	    String Optset;
	    String Opt;
	    String Price;
	    char suffix1 = '1';
	    char suffix2 = 'a';
	    String optset;
	    String opt;
	    String price;
	    Optset = "OptionSet" + suffix1;
	    // seeking for all option set
	    while ((optset = prop.getProperty(Optset)) != null) {
		suffix2 = 'a';
		Opt = "Option" + suffix1 + suffix2;
		at.setOptset(optset);
		// iterate over all options
		while ((opt = prop.getProperty(Opt)) != null) {
		    Price = Opt + "Price";
		    if ((price = prop.getProperty(Price)) != null) {// with
			// price
			at.setOpt(optset, opt, Float.parseFloat(price));
		    } else {// no price provided
			at.setOpt(optset, opt);
		    }
		    suffix2++;
		    Opt = "Option" + suffix1 + suffix2;
		}
		suffix1++;
		Optset = "OptionSet" + suffix1;
	    }

	    return at;

	}

	throw new AutoException("FileFormatError");

    }

    /**
     * read a file with arbitrary file type
     * 
     * @param filename
     *            file name
     * @param filetype
     *            file type
     * @return automobile
     * @throws AutoException
     */
    public Automobile readFileT(String filename, int filetype)
	    throws AutoException {
	Automobile at;
	if (filetype == 2) {// properties file
	    Properties prop = this.loadFile(filename);
	    at = this.readObj(prop);
	    return at;
	} else if (filetype == 1) {
	    at = this.readFile(filename);
	    return at;
	}
	throw new AutoException("FileFormatError");
    }

    /**
     * upload file from client to server
     * 
     * @param target
     *            client socket connected to a server
     * @param filename
     *            the file to be uploaded
     * @throws IOException
     */
    public void upLoad(Socket target, String filename) throws IOException {
	File f = new File(filename);
	int len = (int) f.length();

	ObjectOutputStream oos = new ObjectOutputStream(
		target.getOutputStream());
	oos.writeUTF(filename);// mark file name
	oos.writeLong(len); // mark file length

	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
		filename));
	byte[] buf = new byte[1024];
	ois.readFully(buf);
	oos.write(buf, 0, len);// mark the file body
	ois.close();
    }

    /**
     * down load a file from client at server
     * 
     * @param target
     *            client socket
     * @throws IOException
     */
    public void downLoad(Socket target) throws IOException {
	ObjectInputStream ois = new ObjectInputStream(target.getInputStream());
	String filename = ois.readUTF();// get name
	int len = (int) ois.readLong();// get length
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
		filename));
	byte[] buf = new byte[1024];
	ois.readFully(buf);// get file body
	oos.write(buf, 0, len);
	oos.close();

    }
}
