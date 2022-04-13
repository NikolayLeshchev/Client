package by.palto.command.impl.client;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.domain.entity.Client;
import by.palto.message.Message;

public class DeleteClientCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {
        message.setCommand(CommandEnum.DELETE_CLIENT);
        Message response = connection.formMessage(message);

        Client client = (Client) response.getByKey("deleteClient");

        response.add("client", client);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {

            return CommandEnum.DELETE_CLIENT;
    }
}
