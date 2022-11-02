package server;

import java.util.*;

public class Printer {

    public String name;
    public Map<Integer, String> queue = new HashMap<>();
    public int queueSequence = 0;

    public Printer(String name) {
        this.name = name;
    }

    public void print(String file) {
        System.out.println("--------------------PRINT-----------------------");
        System.out.println("Invocation of print command for printer '" + name +  "', filename '" + file + "'");
        if (queue.containsValue(file)) {
            System.out.println("The file '" + file + "' is already in the queue!");
        } else {
            addToQueue(file);
            System.out.println("File '" + file +  "' successfully added to the print queue with job number " + queueSequence);
        }
    }

    private void addToQueue(String file) {
        queue.put(++queueSequence, file);
    }

    //TODO doesn't work properly. Needs to be fixed
    public void topQueue(int job) {
        System.out.println("--------------------TOP_QUEUE--------------------");
        System.out.println("Invocation of topQueue command for printer '" + name + "'");
        StringBuilder data = new StringBuilder();
        Map<Integer, String> newQueue = new HashMap<>();
        String f = queue.get(job);
        newQueue.put(1, f);
        for(Map.Entry<Integer, String> entry : queue.entrySet()) {
            int j = entry.getKey();
            if (j > job && j != queueSequence) {
                newQueue.put(--j, entry.getValue());
            } else if (j < job) {
                System.out.println("bef: " + entry.getValue());
                newQueue.put(++j, entry.getValue());
                System.out.println("aft " + entry.getValue());
            } else {
                newQueue.put(queueSequence, entry.getValue());
            }
        }
        queue.clear();
        queue.putAll(newQueue);
        for (Map.Entry<Integer, String> entry : queue.entrySet()) {
            data.append("job number: " + entry.getKey() + ", file name: " + entry.getValue() + "\n");
        }
        System.out.println("Job '" + job + "' with filename '" + queue.get(1) + "' successfully moved to the top of the queue \n");
        System.out.println("A new queue for printer '" + name + "' :" +  "\n");
        System.out.println(data);
    }

    public void queue() {
        System.out.println("---------------------QUEUE----------------------");
        System.out.println("Invocation of queue command for printer '" + name + "'\n");
        StringBuilder data = new StringBuilder();
        for (Map.Entry<Integer, String> entry : queue.entrySet()) {
            data.append("job number: " + entry.getKey() + ", file name: " + entry.getValue() + "\n");
        }
        System.out.println(data);
    }

    public void clear() {
        System.out.println("Clearing print queue for printer '" + name + "'...");
        queueSequence = 0;
        queue.clear();
    }
}
