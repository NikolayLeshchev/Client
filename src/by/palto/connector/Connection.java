package by.palto.connector;


import by.palto.message.Message;

public interface Connection {

    int PORT = 4547;

    void send(Message message);

    Message formMessage(Message message);

    void close();
}
