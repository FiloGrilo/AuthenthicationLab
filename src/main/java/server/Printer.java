package src.main.java.server;

import java.util.Map;

//TODO needs to be implemented
public class Printer implements IPrinter{
    @Override
    public String print(String file, String printer) {
        System.out.println("printer prints... " + file);
        return null;
    }

    @Override
    public Map<Integer, String> queue(String printer) {
        return null;
    }

    @Override
    public void topQueue(String printer, Integer job) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

    }

    @Override
    public String status() {
        return null;
    }

    @Override
    public String readConfig() {
        return null;
    }

    @Override
    public String setConfig(String parameter, String value) {
        return null;
    }
}
