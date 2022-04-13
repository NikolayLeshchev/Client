package by.palto.command.impl;

import by.palto.command.Command;
import by.palto.command.CommandEnum;
import by.palto.connector.Connection;
import by.palto.connector.Connector;
import by.palto.message.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class GetAllChartsCommandImpl implements Command {

    private Connection connection = Connector.getInstance().getConnection();

    @Override
    public Message execute(Message message) {
        //
        message.setCommand(CommandEnum.GET_ALL_CHARTS_NAMES);
        Message response = connection.formMessage(message);
        List<String> chartsList = (List<String>) response.getByKey("charts");

        ObservableList<String> chartsPropertyList = FXCollections.observableList(chartsList);

        response.add("charts", chartsPropertyList);

        return response;
    }

    @Override
    public CommandEnum getCommandName() {
        return CommandEnum.GET_ALL_CHARTS_NAMES;
    }
}
