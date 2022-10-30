package server;

public class PrinterQueue {
    public int jobNumber;
    public String filename;

    public PrinterQueue(int jobNumber, String filename) {
        this.jobNumber = jobNumber;
        this.filename = filename;
    }
}
