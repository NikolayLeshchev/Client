package by.palto.command.impl.credit;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.message.Message;

public class GetCreditTypeIdByNameCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {
        message.setCommand(CommandEnum.GET_ID_CREDIT_TYPE_BY_NAME);
        Message response = connection.formMessage(message);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.GET_ID_CREDIT_TYPE_BY_NAME;
    }
}
