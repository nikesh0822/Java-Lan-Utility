
package com.project.jlm.core.message.reader;

import com.project.jlm.core.message.Message;
import com.project.jlm.core.message.filters.FilterTypes;
import com.project.jlm.core.messenger.Messenger;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author project
 */

/*
 * MessageReader.java
 * 
 * this class impliments a message reader
 */
public class MessageReader {

    // the message object
    private Message message;
    // the messenger reference object
    private Messenger messengerInterface;
    // flag to stop reading
    private boolean stop = false;
    // runnable Reader class object
    private Reader reader = null;
    // thread for reader runnable
    private Thread thread = null;

    /*
     * privatising default constructor inorder to prevent usage
     */
    private MessageReader() {
    }

    /*
     * class constructor with message object and messenger reference object as parameter
     */
    public MessageReader(Message msg, Messenger msngrInterface) {
        // set the message object
        this.message = msg;
        // set the messenger object
        this.messengerInterface = msngrInterface;
        // create reader
        reader = new Reader();
        // create thread for the reader
        thread = new Thread(reader);
    }

    /*
     * method to start reading messages
     */
    public void startReading() {
        // set stop flag as false
        stop = false;
        // start the thread for reader runnable
        thread.start();
    }

    /*
     * method to stop reading messages
     */
    public void stopReading() {
        // set stop flag to true
        stop = true;
    }

    /*
     * Reader Class
     * 
     * this runnable class impliments the run method to read the message 
     * continiously from the messenger
     */
    private class Reader implements Runnable {

        @Override
        public void run() {
            // read till stop equals true
            while (!stop) {
                try {
                    // read the message from messenger and set it to the message object
                    message.setMessage(messengerInterface.recieveMessage());
                } catch (IOException ex) {
                    Logger.getLogger(MessageReader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    // null pointer exception found when the other side connection is closed
                    // so inform the user through the message
                    message.setMessage((char) FilterTypes.TEXT_FILTER + "INFO: Other side connection closed.");
                    // stop the message reading
                    stop = true;
                }
            }
        }
    }
}
