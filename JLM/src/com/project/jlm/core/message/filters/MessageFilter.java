
package com.project.jlm.core.message.filters;

import com.project.jlm.core.message.Message;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MessageFilter implements PropertyChangeListener {

    // the raw message
    private Message rawMessage = null;
    // the filtered message 
    private Message filteredMessage = null;
    // filter type variable
    private int filterType = 0;

    /*
     * class constructor with input message and the filter type
     */
    public MessageFilter(Message inputMessage, int filterType) {
        // set the filter type
        this.setFilterType(filterType);
        // set the input raw message
        this.setInputMessage(inputMessage);
    }

    /*
     * getter and setter for filter types
     */
    public int getFilterType() {
        return filterType;
    }

    public final void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    /*
     * Getter for the filter message
     * 
     * Note:- NO need for filtered message setter
     */
    public Message getFilteredMessage() {
        return filteredMessage;
    }

    /*
     * method to set the input message as the raw message
     */
    public final void setInputMessage(Message inputMessage) {
        // set the input message as the raw message
        rawMessage = inputMessage;
        // add this filter as a listner to the raw message object
        rawMessage.addPropertyChangeListener(this);
        // create the filtered message
        filteredMessage = new Message();
        // assign the filterd message messenger with the raw message messenger
        filteredMessage.setMessenger(rawMessage.getMessenger());
    }

    /*
     * method invoked on property change event
     * - ie raw message message content changed event
     */
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            // if the message content type is equal to the assigned filter type then
            if (rawMessage.getMessage().charAt(FilterTypes.TYPE_LOC_INDEX) == (char) filterType) {
                // set the filtered message content
                // - remove the content filter type value
                filteredMessage.setMessage(rawMessage.getMessage().substring(1));
            }
        } catch (StringIndexOutOfBoundsException ex) {
            // runtime error skipping` ...                
        }
    }
}
