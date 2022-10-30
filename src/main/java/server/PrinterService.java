package server;

import java.util.HashMap;
import java.util.Map;


//TODO needs to be implemented
public class PrinterService implements IPrinterService {
    private boolean isStarted;
    private final Map<String, Printer> printers;
    private Map<String, String> configs = new HashMap<>();

    public PrinterService(Map<String, Printer> printers) {
        this.printers = printers;
    }

    @Override
    public void print(String file, String printer) {
        Printer p = printers.get(printer);
        if(!isStarted) {
            System.out.println("Printer server is not started!");
        } else if (p == null) {
            System.out.println("Printer with name: " + printer + " not found!");
        } else {
            p.print(file);
        }
    }

    @Override
    public void queue(String printer) {
        Printer p = printers.get(printer);
        if(!isStarted) {
            System.out.println("Printer server is not started!");
        } else if (p == null) {
            System.out.println("Printer with name: " + printer + " not found!");
        } else {
            p.queue();
        }
    }

    @Override
    public void topQueue(String printer, Integer job) {
        Printer p = printers.get(printer);
        if(!isStarted) {
            System.out.println("Printer server is not started!");
        } else if (p == null) {
            System.out.println("Printer with name: " + printer + " not found!");
        } else {
            p.topQueue(job);
        }
    }

    @Override
    public void start() {
        System.out.println("Printer server started");
        isStarted = true;
    }

    @Override
    public void stop() {
        System.out.println("Printer server stopped");
        isStarted = false;
    }

    @Override
    public void restart() {
        System.out.println("Invocation of restart command - stoppping printing service, clearing prints queue" +
                "and starting printing service again");
        stop();
        printers.values().forEach(Printer::clearQueue);
        start();
    }

    @Override
    public void status() {
        if (isStarted) {
            System.out.println("Printer server is started and can be used");
        } else {
            System.out.println("Printer server is not started and can not be used");
        }
    }

    @Override
    public void readConfig(String parameter) {
        if(!isStarted) {
            System.out.println("Printer server is not started!");
        } else {
            System.out.println("The value of the parameter " + parameter + " :" + configs.get(parameter));
        }
    }

    @Override
    public void setConfig(String parameter, String value) {
        if(!isStarted) {
            System.out.println("Printer server is not started!");
        } else {
            System.out.println("Invocation of setConfig command - set the " + parameter + " to the " + value);
            configs.put(parameter, value);
        }
    }

}
