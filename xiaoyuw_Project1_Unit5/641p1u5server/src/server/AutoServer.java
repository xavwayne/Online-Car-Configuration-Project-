/*
 * Andrew ID: xiaoyuw
 */
package server;

import java.io.*;
import java.util.*;
import model.*;

/**
 * Interface for server to build an automobile, add it to the LHM, show
 * available models on server and send a model to client.
 */
public interface AutoServer {

    public void addAuto(Automobile at);

    public Automobile buildModel(Properties prop);

    public String showAvailable();

    public void sendCar(ObjectInputStream in, ObjectOutputStream out);
}
