package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

public class Scheduler {
    Queue<Integer> priorityQueue;
    Map<Integer, Integer> idAndBurstTimeMap;
    ProcessWareHouse processWareHouse;

    public Scheduler() {
        this.priorityQueue = new LinkedList();
        this.idAndBurstTimeMap = new HashMap<Integer, Integer>();
    }


    // main function
    public void connectToProcessWareHouse(ProcessWareHouse processWareHouse) {
        try {
            this.processWareHouse = processWareHouse;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    //end

    public void start() {
        createIdAndBurstTimeMap();
        createPriorityQueueFromMap();
    }

    // for return process from scheduler
    public Process getProcess() {
        int id = priorityQueue.poll();
        if (searchProcessById(id) != null)
            return searchProcessById(id);
        else
            return null;
    }
    // end

    public Process searchProcessById(int id) {
        Iterator<Process> itr = this.processWareHouse.jobQueue.iterator();

        while (itr.hasNext()) {
            Process currentProcess = itr.next();
            int currentId = currentProcess.processControlBlock.getId();
            if (currentId == id) return currentProcess;
        }
        return null;
    }

    // create the hashmap to later on feed to priority queue
    public void createIdAndBurstTimeMap() {
        for (Object process : this.processWareHouse.readyQueue) {
            addProcessToMap((Process) process);
        }
    }

//    public boolean removeProcessFromMap(Process process) {
//        try {
//            int id = process.processControlBlock.getId();
//            if (idAndBurstTimeMap.containsKey(id))
//                idAndBurstTimeMap.remove(id);
//            return true;
//        } catch (Exception e) {
//            Utilities.printErr(e.getMessage());
//            return false;
//        }
//    }


    public boolean addProcessToMap(Process process) {
        try {
            if (Utilities.isValidProcess(process)) {
                idAndBurstTimeMap.put(process.processControlBlock.getId(),
                        process.processControlBlock.getPriority());
                return true;
            } else {
                Utilities.printErr("Cannot add to map");
                return false;
            }
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
            return false;
        }
    }

    // Priority scheduling algorithm
    public void createPriorityQueueFromMap() {
        LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();

        idAndBurstTimeMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<Integer, Integer> en : sortedMap.entrySet()) {
            priorityQueue.offer(en.getKey());
        }
    }

    //
    public void printPriorityQueue() {
        for (int id : this.priorityQueue) {
            Utilities.print("The process id is: " + id);
        }
    }
}
