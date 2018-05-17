
package com.project.jlm.core.messenger;

import java.io.IOException;

/**
 *
 * @author project
 */

public interface Messenger {

  
    public void connect(String ip, int portId) throws IOException;

    public void sendMessage(String message) throws IOException;

    public String recieveMessage() throws IOException;

    public void disconnect() throws IOException;

    public boolean isConnected() throws IOException;
}
