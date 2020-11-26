package com.company;

import java.util.*;

public class Scheduler {
    Queue<Integer> priorityQueue;
    Map<Integer, Integer> idAndBurstTimeMap;
    ProcessWareHouse processWareHouse;
    Dispatcher dispatcher;

    public Scheduler() {
        this.priorityQueue = new LinkedList<Integer>();
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

    public void connectToDispatcher(Dispatcher dispatcher) {
        try {
            this.dispatcher = dispatcher;
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
    // take a process from priority Q, take it out of ready Q
    public Process getProcess() {
//        depend on the method, if PQ then pull the id out of PQ, search for that process, execute that process and erase that process
//        if RR, just pull the most current process, execute a while and put it back

        if (OperatingSystem.isPriorityQueueMethod()) {
            Process process = getProcessIfPriorityQueueMethod();
            return process;
        }

        return null;
    }

    public Process getProcessIfPriorityQueueMethod() {
        if (priorityQueue.size() > 0) {
            int id = priorityQueue.poll();
            Process process = this.processWareHouse.searchProcessById(id);
            if (process != null) {
                return process;
            } else
                return null;
        } else {
            Utilities.print("Priority Queue is empty");
            return null;
        }
    }
    // end

//    public Process searchProcessById(int id) {
//        Collection readyQueue = processWareHouse.getReadyQueue();
//
//        Iterator<Process> itr = readyQueue.iterator();
//
//        while (itr.hasNext()) {
//            Process currentProcess = itr.next();
//            int currentId = currentProcess.processControlBlock.getId();
//            if (currentId == id) return currentProcess;
//        }
//        return null;
//    }

    // create the hashmap to later on feed to priority queue
    public void createIdAndBurstTimeMap() {
        for (Object process : this.processWareHouse.readyQueue) {
            addProcessToMap((Process) process);
        }
    }


    public boolean addProcessToMap(Process process) {
        try {
            if (process.processControlBlock.getId() > 0) {
                this.dispatcher.changeStateToNew(process);
                this.dispatcher.changeStateToReady(process);
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
