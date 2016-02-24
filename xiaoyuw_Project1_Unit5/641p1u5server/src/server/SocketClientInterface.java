/*
 * Andrew ID: xiaoyuw
 */
package server;

/**
 * Interface -- provide methods to establish connection, handle requests and
 * close connection
 */
public interface SocketClientInterface {
    
    public boolean openConnection();

    public void handleSession();

    public void closeSession();
}
