
package com.project.jlm.core.messenger.client;

import com.project.jlm.core.messenger.MessengerAbstraction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author project
 */

public class MessengerClient extends MessengerAbstraction {

    /*
     * connect method as defined in the MessengerInterface
     * used to connect to a server with ip and portId
     */
    public void connect(String ip, int portId) throws IOException {
        // creating the socket
        this.setSocket(new Socket(ip, portId));
        // set the inputstream
        this.setInputStream(this.getSocket().getInputStream());
        // set the buffered reader using the input stream; for reading data
        this.setBufferedReader(new BufferedReader(new InputStreamReader(this.getInputStream())));
        // set the print writer; for writing data
        this.setPrintWriter(new PrintWriter(this.getSocket().getOutputStream(), true));
    }
}
