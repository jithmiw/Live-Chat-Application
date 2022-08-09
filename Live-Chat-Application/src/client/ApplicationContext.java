package client;

import client.util.StreamConfiguration;

public class ApplicationContext {
    private static final StreamConfiguration streamConfiguration = new StreamConfiguration();

    public static StreamConfiguration getStreamConfiguration() {
        return streamConfiguration;
    }
}
