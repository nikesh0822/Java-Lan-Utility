
package com.project.jlm.core.messenger.server;

import com.project.jlm.core.messenger.MessengerAbstraction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 *
 * @author project
 */
public class MessengerServer extends MessengerAbstraction {

    public void connect(String ip, int portId) throws IOException {
        // starting the server at port
        this.setServerSocket(new ServerSocket(portId));
        // creating the socket
        this.setSocket(this.getServerSocket().accept());
        // set the inputstream
        this.setInputStream(this.getSocket().getInputStream());
        // set the buffered reader using the input stream; for reading data
        this.setBufferedReader(new BufferedReader(new InputStreamReader(this.getInputStream())));
        // set the print writer; for writing data
        this.setPrintWriter(new PrintWriter(this.getSocket().getOutputStream(), true));
    }
}
