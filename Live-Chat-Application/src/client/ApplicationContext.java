package client;

import client.util.MessageConfiguration;
import client.util.StreamConfiguration;

public class ApplicationContext {
    private static final StreamConfiguration streamConfiguration = new StreamConfiguration();
    private static final MessageConfiguration messageConfiguration = new MessageConfiguration();

    public static StreamConfiguration getStreamConfiguration() {
        return streamConfiguration;
    }
    public static MessageConfiguration getMessageConfiguration() {
        return messageConfiguration;
    }
}
