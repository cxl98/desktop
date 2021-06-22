package com.cxl.common.util;

import com.cxl.common.command.Commands;
import com.cxl.common.command.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cxl
 */
public class CommandHandlerLoader {
    private String name;
    private static final Logger LOGGER= LoggerFactory.getLogger(CommandHandlerLoader.class);
    private static final Map<Enum<Commands>, Handler> HANDLER=new ConcurrentHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Handler getCommandHandler(Enum<Commands> command){
        if (null==command){
            return null;
        }
        Handler handler = HANDLER.get(command);
        if (handler == null) {
            return null;
        }
        synchronized (CommandHandlerLoader.class){
           Handler handler1= loadCommandHandler(command);
           HANDLER.put(command,handler1);
           return handler1;
        }
    }

    private Handler loadCommandHandler(Enum<Commands> command) {
        return null;
    }
}
