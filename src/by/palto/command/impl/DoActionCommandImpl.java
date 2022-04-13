package by.palto.command.impl;

import by.palto.command.Command;

import by.palto.command.CommandEnum;
import by.palto.domain.entity.Client;
import by.palto.message.Message;

public class DoActionCommandImpl implements Command {


    private String commandName;

    public DoActionCommandImpl() {
    }

    public DoActionCommandImpl(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public CommandEnum getCommandName() {
        return null;
    }

    @Override
    public Message execute(Message message) {

        Client client = (Client) message.getObject();
        return  new Message();
    }
}


