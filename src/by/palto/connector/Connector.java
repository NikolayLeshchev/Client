package by.palto.connector;

import java.net.InetAddress;
import java.net.Socket;

public class Connector implements ConnectionListener {

    private static final Connector instance = new Connector();

    public static Connector getInstance() {
        return instance;
    }

    private Connector() {
        createConnection();
    }

    ///////////////////////////////////////////
    private static Connection connection;

    private void createConnection() {

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), Connection.PORT);
            connectionCreated(connection = new ConnectionListenerImpl(socket, this));
           // return connection;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
       // return connection;
    }

    public  Connection getConnection() {
        return connection;
    }

    @Override
    public void connectionCreated(Connection c) {
        System.out.println("Connection successful!");
    }

    @Override
    public void connectionClose(Connection c) {
        System.out.println("Connection ended");
        c.close();
    }

    @Override
    public void connectionException(Exception ex) {
        ex.printStackTrace();
    }
}
