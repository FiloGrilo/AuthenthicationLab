package server;


public interface IPrinterService {
    void print (String file, String printer);
    void queue (String printer);
    void topQueue(String printer, Integer job);
    void start();
    void stop();
    void restart();
    void status();
    void readConfig(String parameter);
    void setConfig(String parameter, String value);
}
