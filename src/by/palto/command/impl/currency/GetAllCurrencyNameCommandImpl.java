package by.palto.command.impl.currency;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetAllCurrencyNameCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {

        message.setCommand(CommandEnum.GET_ALL_CURRENCY_NAMES);
        Message response = connection.formMessage(message);
        List<String> currencyList = (List<String>) response.getByKey("currency");

        ObservableList<String> currencyPropertyList = FXCollections.observableList(currencyList);

        response.add("currency", currencyPropertyList);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.GET_ALL_CURRENCY_NAMES;
    }
}
