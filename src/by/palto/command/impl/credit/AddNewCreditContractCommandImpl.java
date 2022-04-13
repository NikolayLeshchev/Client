package by.palto.command.impl.credit;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.message.Message;

public class AddNewCreditContractCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {

        message.setCommand(CommandEnum.ADD_NEW_CREDIT_CONTRACT);
        Message response = connection.formMessage(message);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.ADD_NEW_CREDIT_CONTRACT;
    }

}
