package server;


public interface IPrinterService {
    String print (String file, String printer);
    String queue (String printer);
    void topQueue(String printer, Integer job);
    void start();
    void stop();
    void restart();
    String status();
    String readConfig(String parameter);
    void setConfig(String parameter, String value);
}
