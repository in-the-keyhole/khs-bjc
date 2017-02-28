package com.khs.hystrix.hcommand;


import javax.servlet.http.HttpServletRequest;

import org.bjc.clindesk.server.Command;
import org.bjc.clindesk.server.CommandException;
import org.bjc.clindesk.server.Dispatchable;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public abstract class AbstractHystrixCommand extends HystrixCommand<Dispatchable> implements Command {

    protected HttpServletRequest request;

    public AbstractHystrixCommand(HystrixCommandGroupKey group) {
        super(group);
    }

    public AbstractHystrixCommand(Setter setter) {
        super(setter);
    }

    @Override
    public Dispatchable execute(HttpServletRequest request) throws CommandException {
        this.request = request;
        try {
            return run();
        } catch (Exception e) {
            throw new CommandException();
        }
    }

}
