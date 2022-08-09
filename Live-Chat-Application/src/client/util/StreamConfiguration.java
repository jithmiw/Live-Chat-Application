package client.util;

import javafx.scene.control.Alert;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class StreamConfiguration {
    private int portAddr = 0;
    private Socket socket;
    private String hostAddr;
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

    public void stopStream() {
        try {
            if (streamIn != null) streamIn.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred closing the application. Shutdown initiated.").show();
            System.exit(0);
        }

    }

    public DataInputStream getStreamIn() {
        return streamIn;
    }

    public void setStreamIn(DataInputStream streamIn) {
        this.streamIn = streamIn;
    }

    public DataOutputStream getStreamOut() {
        return streamOut;
    }

    public void setStreamOut(DataOutputStream streamOut) {
        this.streamOut = streamOut;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getHostAddr() {
        return hostAddr;
    }

    public void setHostAddr(String hostAddr) {
        this.hostAddr = hostAddr;
    }

    public int getPortAddr() {
        return portAddr;
    }

    public void setPortAddr(int portAddr) {
        this.portAddr = portAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
