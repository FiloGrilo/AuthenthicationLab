package server;

import java.util.*;

public class Printer {

    public String name;
    public LinkedList<String> queue = new LinkedList<>();

    public Printer(String name) {
        this.name = name;
    }

    public void print(String file) {
        System.out.println("Invocation of print command - the printer " + name + " prints: " + file);
        if (queue.contains(file)) {
            System.out.println("The file: " + file + " is already in the queue");
        } else {
            addToQueue(file);
        }
    }

    private void addToQueue(String file) {
        queue.add(file);
    }

    public void topQueue(int job) {
        queue.add(0, queue.get(job));
        System.out.println("A new print queue for the printer " + name + " is:" +  "\n");
        StringBuilder data = new StringBuilder();
        for (String s : queue) {
            data.append("job number: " + queue.indexOf(s) + "\n" +
                    "file name: " + s + "\n\n");
        }
        System.out.println(data);
    }

    public void queue() {
        System.out.println("The print queue for the printer " + name + " is :");
        StringBuilder data = new StringBuilder();
        for (String s : queue) {
            data.append("job number: " + queue.indexOf(s) + "\n" +
                    "file name: " + s + "\n\n");
        }
        System.out.println(data);
    }

    public void clearQueue() {
        queue.clear();
    }
}
