package by.palto.command.impl.deposit;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetAllDepositTypesNameCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {
        message.setCommand(CommandEnum.GET_ALL_DEPOSIT_TYPES_NAMES);
        Message response = connection.formMessage(message);
        List<String> depositTypes = (List<String>) response.getByKey("depositTypes");

        ObservableList<String> chartsPropertyList = FXCollections.observableList(depositTypes);

        response.add("depositTypes", chartsPropertyList);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.GET_ALL_DEPOSIT_TYPES_NAMES;
    }
}
