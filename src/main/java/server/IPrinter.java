package src.main.java.server;

import java.util.Map;

interface IPrinter {
    String print (String file, String printer);
    Map<Integer, String> queue (String printer);
    void topQueue(String printer, Integer job);
    void start();
    void stop();
    void restart();
    String status();
    String readConfig();
    String setConfig(String parameter, String value);
}
