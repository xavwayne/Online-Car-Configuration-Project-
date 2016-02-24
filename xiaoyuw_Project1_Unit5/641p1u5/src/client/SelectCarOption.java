/*
 * Andrew ID: xiaoyuw
 */
package client;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import model.*;

/**
 * SelectCarOption--show available models on server, select a model and
 * configure that model at client side
 */
public class SelectCarOption {

	private String choice;
	private Automobile auto;
	private String cars;

	/**
	 * create a thread to show available cars on server
	 */
	public synchronized String showCars() {
		String HOST = null;
		try {
			HOST = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DefaultSocketClient dsc = new DefaultSocketClient(HOST, SocketClientConstants.TEST_PORT, this, 2);
		dsc.start();
		try {
			dsc.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		String cars[]=this.cars.split("\t");
		System.out.println(cars.length);
		System.out.println(0+cars[0]);
		System.out.println(1+cars[1]);*/
		return this.cars;
	}

	/**
	 * create a thread to select a model. The selected model will be send to
	 * client.
	 */
	public synchronized Automobile select() {
		String HOST = null;
		try {
			HOST = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DefaultSocketClient dsc = new DefaultSocketClient(HOST, SocketClientConstants.TEST_PORT, this, 3);
		dsc.start();
		try {
			dsc.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return this.auto;
	}
	
	
	/**
	 * The process to select a model interact with server
	 * 
	 * @param in
	 *            inpustream
	 * @param out
	 *            outputstream
	 * @throws Exception
	 */
	public void select(ObjectInputStream in, ObjectOutputStream out) throws Exception {
//		System.out.println("Which model would you like to choose?");
//		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
//		this.choice = rd.readLine();
		out.writeObject(new Integer(3));// request head
		out.writeObject(choice);// request body
		Automobile at = (Automobile) in.readObject();
		this.auto = at;

	}

	/**
	 * show options of the selected model
	 */
	public synchronized void printOptions() {
		this.auto.print();
	}

	/**
	 * Process to configure the model at client side
	 * 
	 * @throws Exception
	 */
	public synchronized void configure() throws Exception {
		while (true) {
			System.out.println(
					"Set your configuration with format \"OptionSet:Optoin\". Type 'q' to finish configuration.");
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

			String config = rd.readLine();
			if (config.equalsIgnoreCase("q"))
				break;
			String[] s = config.split(":");
			if (s.length < 2) {
				System.out.println("Format Error. please retype \"OptionSet:Optoin\"");
				continue;
			}
			String optset = s[0];
			String opt = s[1];
			try {
				this.auto.setOptionChoice(optset, opt);
			} catch (Exception e) {
				System.out.println("Error : Wrong Option set / Option!");
				continue;
			}
		}
		System.out.println("Configuration done!");
		auto.displayOptionChoice();
	}

	public void setCars(String models) {
		this.cars=models;		
	}
	
	public void setChoice(String choice){
		this.choice=choice;
	}
}
