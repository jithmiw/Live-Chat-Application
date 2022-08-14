package client;

import client.controllers.ClientFormController;

import java.io.DataInputStream;
import java.net.SocketException;

public class ChatClientThread {

    private final ClientFormController client;
    private final DataInputStream streamIn;

    public ChatClientThread(ClientFormController client) {
        this.client = client;
        streamIn = ApplicationContext.getStreamConfiguration().getStreamIn();
    }

    public void run() {
        while (true) {
            try {
                client.handle(streamIn.readUTF());
            } catch (SocketException se) {
                if (Thread.activeCount() > 1) {
                    client.handle("An error occurred. Please restart the client...");
                    break;
                }
                se.printStackTrace();
            } catch (Exception e) {
                ApplicationContext.getStreamConfiguration().stopStream();
                e.printStackTrace();
                break;
            }
        }
    }
}
