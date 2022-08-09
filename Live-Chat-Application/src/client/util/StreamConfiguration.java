package client.util;

import javafx.scene.control.Alert;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class StreamConfiguration {
    private Socket socket;
    private String hostAddr;
    private final int portAddr = 0;
    private String name;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;

    public void initSocket() throws IOException {
        if (hostAddr.isEmpty() || portAddr == 0) {
            new Alert(Alert.AlertType.INFORMATION, "Invalid host address or port address. Please try again...").show();
            return;
        }
        this.socket = new Socket(hostAddr, portAddr);
    }

    public void initStream() {
        try {
            streamIn = new DataInputStream(socket.getInputStream());
            streamOut = new DataOutputStream(socket.getOutputStream());
            streamOut.writeUTF(name);
            streamOut.flush();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while initializing streams. Please check the server and try again...").show();
            System.exit(0);
        }
    }
}
