package client;

import client.controllers.ClientFormController;

import java.io.DataInputStream;

public class ChatClientThread {

    private final ClientFormController client;
    private final DataInputStream streamIn;

    public ChatClientThread(ClientFormController client) {
        this.client = client;
        streamIn = ApplicationContext.getStreamConfiguration().getStreamIn();
    }
}
