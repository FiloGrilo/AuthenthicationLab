package server;

import java.util.Map;

public interface IPrinter {
    String print (String file, String printer);
    Map<Integer, String> queue (String printer);
    void topQueue(String printer, Integer job);
    void start();
    void stop();
    void restart();
    String status();
    String readConfig(String parameter);
    String setConfig(String parameter, String value);
}
