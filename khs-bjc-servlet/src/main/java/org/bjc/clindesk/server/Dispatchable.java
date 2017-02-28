package org.bjc.clindesk.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public abstract class Dispatchable {

    protected Object content;
    protected OutputStream out;
    protected PrintWriter writer;
    protected boolean compressed = false;

    public void dispatch(OutputStream os) throws IOException {
        out = os;
        doDispatch();
    }
    public void dispatch(PrintWriter w) throws IOException {
        writer = w;
        doDispatch();
    }

    public abstract boolean isWriter();

    protected abstract void doDispatch() throws IOException;

    public void setCompressed(boolean c) {
        compressed = c;
    }

    public boolean isCompressed() {
        return compressed;
    }

    public Object getContent() {
        return content;
    }

    public String getContentType(){
        //Default implementatiopn.  Override as needed.
        return isWriter() ? "text/xml;charset=utf-8" : "application/x-java-serialized-object";
    }


    public void dispatch(HttpServletResponse response) throws IOException {
        response.setContentType(getContentType());
        if (isWriter()) {
            dispatch(response.getWriter());
        } else {
            dispatch(response.getOutputStream());
        }
    }

}
