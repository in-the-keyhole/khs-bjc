package org.bjc.clindesk.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.DeflaterOutputStream;

public class ObjectDispatchable extends Dispatchable {
    private boolean error;

    public ObjectDispatchable(Object data) {
        content = data;
        error = false;
    }

    public ObjectDispatchable(Object data, boolean err) {
        content = data;
        error = err;
    }

    public boolean isWriter() {
        return false;
    }

    protected void doDispatch() throws IOException {
        ObjectOutputStream output = null;
        BufferedOutputStream bs = new BufferedOutputStream(out);
        if (compressed) {
            output = new ObjectOutputStream(new DeflaterOutputStream(bs));
        } else {
            output = new ObjectOutputStream(bs);
        }
        try {
            if (error) {
                output.writeObject(content);
            } else {
                output.writeObject(content);
            }
            output.flush();
        } catch (IOException io) {
            output.close();
            throw io;
        }
        output.close();
    }
}
