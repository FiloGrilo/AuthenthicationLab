package server;

import java.util.HashMap;
import java.util.Map;

public class PrinterService implements IPrinterService {
    private boolean isStarted;
    private final Map<String, Printer> printers;
    private Map<String, String> configs = new HashMap<>();

    public PrinterService(Map<String, Printer> printers) {
        this.printers = printers;
    }

    @Override
    public String print(String file, String printer) {
        Printer p = printers.get(printer);
        if(!isStarted) {
            return "PRINT - Printer server is not started yet!\n";
        } else if (p == null) {
            return "\nPRINT - Printer with name: '" + printer + "' not found!\n";
        }
        return p.print(file);
    }

    @Override
    public String queue(String printer) {
        Printer p = printers.get(printer);
        if(!isStarted) {
           return "QUEUE - Printer server is not started!\n";
        } else if (p == null) {
           return "QUEUE - Printer with name: " + printer + " not found!";
        }
        return p.queue();
    }

    @Override
    public void topQueue(String printer, Integer job) {
        Printer p = printers.get(printer);
        if(!isStarted) {
            System.out.println("TOP_QUEUE - Printer server is not started!\n");
        } else if (p == null) {
            System.out.println("TOP_QUEUE - Printer with name: " + printer + " not found!\n");
        } else {
            p.topQueue(job);
        }
    }

    @Override
    public void start() {
        System.out.println("\nSTART - Printer server started...\n");
        isStarted = true;
    }

    @Override
    public void stop() {
        System.out.println("STOP - Printer server stopped.\n");
        isStarted = false;
    }

    @Override
    public void restart() {
        System.out.println("Invocation of restart command..\n");
        stop();
        printers.values().forEach(Printer::clear);
        start();
    }

    @Override
    public String status() {
        System.out.println("Invocation of status command..\n");
        if (isStarted) {
           return "Printer server is started and can be used\n";
        }
        return "Printer server is not started and can not be used\n";
    }

    @Override
    public String readConfig(String parameter) {
        if(!isStarted) {
            return "Printer server is not started!";
        }
        System.out.println("Invocation of readConfig command - read config for parameter '" + parameter + "'\n");
        return "The value of the parameter '" + parameter + "' : " + configs.get(parameter) + "\n";
    }

    @Override
    public void setConfig(String parameter, String value) {
        if(!isStarted) {
            System.out.println("Printer server is not started!");
        } else {
            System.out.println("Invocation of setConfig command - set parameter '" + parameter + "' to the value '" + value + "' \n");
            configs.put(parameter, value);
        }
    }

}
