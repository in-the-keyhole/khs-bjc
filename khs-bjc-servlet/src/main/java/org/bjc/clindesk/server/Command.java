package org.bjc.clindesk.server;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    public Dispatchable execute(HttpServletRequest request) throws CommandException;

}
