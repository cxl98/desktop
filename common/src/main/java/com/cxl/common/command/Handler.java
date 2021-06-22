package com.cxl.common.command;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author cxl
 */
public interface Handler<T> {
    void handle(ChannelHandlerContext context,T in) throws Exception;
}
