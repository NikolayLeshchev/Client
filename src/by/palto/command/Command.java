package by.palto.command;

import by.palto.message.Message;

import java.io.Serializable;

public interface Command extends Serializable {

    Message execute(Message message);//throw command exception

    CommandEnum getCommandName();
}
