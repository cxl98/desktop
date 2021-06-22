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
}
