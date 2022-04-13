package by.palto.message;

import by.palto.command.CommandEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private CommandEnum command;
    private Map<String, T> object;

    public Message() {
        this.object = new HashMap<>();
    }

    public Message(CommandEnum command, Map<String, T> object) {
        this.command = command;
        this.object = object;
    }

    public void setCommand(CommandEnum command) {
        this.command = command;
    }

    public Map<String, T> getObject() {
        return object;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public void add(String key,T object) {
        this.object.put(key, object);
    }

    public T getByKey(String key) {
        return this.object.get(key);
    }
}
