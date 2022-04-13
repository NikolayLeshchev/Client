package by.palto.command.impl.deposit;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.domain.entity.Client;
import by.palto.message.Message;

public class AddNewDepositContactCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {

        message.setCommand(CommandEnum.ADD_NEW_DEPOSIT_CONTRACT);
        Message response = connection.formMessage(message);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.ADD_NEW_DEPOSIT_CONTRACT;
    }

}
