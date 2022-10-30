package server;

import java.util.Map;

//TODO needs to be implemented
public class Printer implements IPrinter{
    @Override
    public String print(String file, String printer) {
        System.out.println("Invocation of print command - the printer " + printer + " prints: " + file);
        return null;
    }

    @Override
    public Map<Integer, String> queue(String printer) {
        System.out.println("Invocation of queue command - lists the printer " + printer + " queue of prints to do");
        return null;
    }

    @Override
    public void topQueue(String printer, Integer job) {
        System.out.println("Invocation of topQueue command - moves job number " + job + " of printer "
                + printer + " to top of queue");
    }

    @Override
    public void start() {
        System.out.println("Invocation of start command - starting printing service");
    }

    @Override
    public void stop() {
        System.out.println("Invocation of stop command - stopping printing service");
    }

    @Override
    public void restart() {
        System.out.println("Invocation of restart command - stoppping printing service, clearing prints queue" +
                "and starting printing service again");
    }

    @Override
    public String status() {
        System.out.println("Invocation of status command - prints the status of the printer");
        return null;
    }

    @Override
    public String readConfig(String parameter) {
        System.out.println("Invocation of readConfig command - prints the value of " + parameter + "" +
                " on user's display");
        return null;
    }

    @Override
    public String setConfig(String parameter, String value) {
        System.out.println("Invocation of setConfig command - set the " + parameter + " to the " + value);
        return null;
    }
}
