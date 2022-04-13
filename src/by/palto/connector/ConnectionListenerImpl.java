package by.palto.connector;

import by.palto.domain.entity.Client;
import by.palto.message.Message;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ConnectionListenerImpl implements Connection, Runnable {

    private boolean needToRun = true;
    //
    private Socket socket;

    private OutputStream out;
    private InputStream in;

    public ConnectionListenerImpl(Socket socket, ConnectionListener connectionListener) throws Exception {
        super();
        this.socket = socket;
        out = socket.getOutputStream();
        in = socket.getInputStream();
        Thread t = new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    @Override
    public void send(Message message) {
        try {
            System.out.println("Message sent");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(message);
            objOut.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void close() {
        try {
            this.needToRun = false;
            this.socket.close();
            this.in.close();
            this.out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Соединение закрыто!");
        }
    }

    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public Message formMessage(Message request) {
//        System.out.println("start dialog");
        send(request);

        while (needToRun) {
            try {
                int amount = in.available();

                if (amount != 0) {

                    System.out.println("Catch answers");
                    System.out.println("Listening socket");

                    ObjectInputStream objIn = new ObjectInputStream(in);
                    Message msq = (Message) objIn.readObject();
                    return msq;
//
//                    // System.out.println("We takes sample.message with type: " + msq.getType());
//                    System.out.println();
//                    // System.out.println(msq.getAdminList().toString());
//                    readMessage(msq);

                } else {
                    Thread.sleep(300);
                }
            } catch (IOException | InterruptedException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        return null;
    }

    public synchronized void readMessage(Message message) {


       if("getAllClients".equals(message.getCommand())){
           List<Client> clients = (List<Client>)message.getObject();
           clients.forEach(System.out::println);
       }

    }

//    private void readMessage(Message msq){

//        if(msq.getType() == Message.SHOW_ALL_ALTERNATIVE){
//            AlternativeActionImpl.setAlternativeList(msq.getAlternativeList());
//        }
//       if(msq.getType() == Message.SHOW_ALL_RANG_TABLe){
//            RangTableActionImpl.setRang(msq.getTableRang());
//        }
//       if(msq.getType() == Message.ADMIN_SHOW_ALL){
//           AdminActionImpl.setAdminPropertyList(msq.getAdminList());
//       }
//       if(msq.getType()==Message.CHECK_ADMIN){
//          // System.out.println(msq.getCheckResult());
//           AdminActionImpl.setCheckResult(msq.getCheckResult());
//       }


}
