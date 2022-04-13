package by.palto.command.impl.client;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.domain.entity.Client;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetAllClientsCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {
        message.setCommand(CommandEnum.GET_ALL_CLIENTS);
        Message response = connection.formMessage(message);
        List<Client> clientList = (List<Client>) response.getByKey("clients");

        ObservableList<Client> clientsPropertyList = FXCollections.observableList(clientList);
        response.add("clients", clientsPropertyList);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.GET_ALL_CLIENTS;
    }
}
