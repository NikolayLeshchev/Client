package by.palto.command.impl.credit;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetAllCreditTypesNameCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {

        message.setCommand(CommandEnum.GET_ALL_CREDIT_TYPES_NAMES);
        Message response = connection.formMessage(message);
        List<String> creditTypes = (List<String>) response.getByKey("creditTypes");

        ObservableList<String> chartsPropertyList = FXCollections.observableList(creditTypes);

        response.add("creditTypes", chartsPropertyList);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.GET_ALL_CREDIT_TYPES_NAMES;
    }
}
