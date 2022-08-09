package client.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class StreamConfiguration {
    private Socket socket;
    private String hostAddr;
    private int portAddr = 0;
    private String name;
    private DataInputStream streamIn;
    private DataOutputStream streamOut;
}
