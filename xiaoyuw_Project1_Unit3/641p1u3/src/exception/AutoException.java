/*
 * Andrew ID: xiaoyuw
 */
package exception;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.*;

/**
 * Customer Exception handler. provides 6 specific exception and the fix method
 * to handle the exception
 */
public class AutoException extends Exception {

    private static final long serialVersionUID = 4L;
    private Err err;
    private Timestamp ts;
    private static Logger logger = Logger.getLogger("LOG");
    private static FileHandler fh;

    /**
     * default constructor
     */
    public AutoException() {

	ts = new Timestamp(new Date().getTime());
	try {
	    if (fh == null)
		logger.addHandler(new FileHandler("mylog.txt"));
	} catch (SecurityException e) {
	    e.printStackTrace();
	} catch (IOException e) {

	    e.printStackTrace();
	}

    }

    /**
     * constructor with arguments
     * 
     * @param msg
     *            error messsage
     */
    public AutoException(String msg) {

	ts = new Timestamp(new Date().getTime());
	try {
	    if (fh == null)
		logger.addHandler(new FileHandler("mylog.txt"));
	} catch (SecurityException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	switch (msg) {
	case "MissingPrice":
	    this.err = Err.MissingPrice;
	    break;
	case "MissingOptionSet":
	    this.err = Err.MissingOptionSet;
	    break;
	case "MissingOption":
	    this.err = Err.MissingOption;
	    break;
	case "InvalidFileName":
	    this.err = Err.InvilidFileName;
	    break;
	case "NoSuchModel":
	    this.err = Err.NoSuchModel;
	    break;
	case "FileFormatError":
	    this.err = Err.FileFormatError;
	    break;
	default:
	    System.out.println("Undefined Exception!");
	}
    }

    /**
     * show error number
     * 
     * @return error number
     */
    public int getErrno() {
	if (err != null) {
	    return err.getErrno();
	} else {
	    System.out.println("Undefined Exception!");
	    return -1;
	}
    }

    /**
     * show error message
     * 
     * @return error message
     */
    public String getErrMsg() {
	if (err != null) {
	    return ts.toString() + "\t" + err.getErrMsg();
	} else {
	    return "Undefined Exception!";
	}
    }

    /**
     * log the exception info to a file
     */
    public void log() {

	logger.info(getErrMsg());
    }

    /**
     * fix problems given error number
     * 
     * @param errno
     *            error number
     * @return the fix result
     */
    public String fix(int errno) {
	String s;
	Fix1to100 f1 = new Fix1to100();
	switch (errno) {
	case 1:
	    s = f1.fix1(errno);
	    break;
	case 2:
	    s = f1.fix2(errno);
	    break;
	case 3:
	    s = f1.fix3(errno);
	    break;
	case 4:
	    s = f1.fix4(errno);
	    break;
	case 5:
	    s = f1.fix5(errno);
	    break;
	case 6:
	    s = f1.fix6(errno);
	    break;
	default:
	    s = "Undefined Exception!";
	    System.out.println("Undefined Exception!");

	}
	return s;
    }

    /**
     * enumeration of errors
     */
    private enum Err {
	MissingPrice(1) {
	    public String getErrMsg() {
		return "Error: Missing Price.";
	    }
	},
	MissingOptionSet(2) {
	    public String getErrMsg() {
		return "Error: Missing Option Set.";
	    }
	},
	MissingOption(3) {
	    public String getErrMsg() {
		return "Error: Missing Option.";
	    }
	},
	InvilidFileName(4) {
	    public String getErrMsg() {
		return "Error: Invalid File Name.";
	    }
	},
	NoSuchModel(5) {
	    public String getErrMsg() {
		return "Error: No such model.";
	    }
	},
	FileFormatError(6) {
	    public String getErrMsg() {
		return "Error: File format error.";
	    }
	};
	private int errno;

	Err(int i) {
	    this.errno = i;
	}

	public int getErrno() {
	    return this.errno;
	}

	public String getErrMsg() {
	    return null;
	}
    }

}
