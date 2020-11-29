package com.company;

import java.util.*;

public class Scheduler {
    Queue<Integer> priorityQueue;
    Map<Integer, Integer> idAndBurstTimeMap;
    ProcessWareHouse processWareHouse;
    Dispatcher dispatcher;
    OperatingSystem osController;

    public Scheduler(OperatingSystem osController) {
        this.priorityQueue = new LinkedList<Integer>();
        this.idAndBurstTimeMap = new HashMap<Integer, Integer>();
        this.osController = osController;
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
        if (OperatingSystem.isPriorityQueueMethod()) {
//            HashMap (key:id - value:priority) to keep history of all the processes that had been added either ready or terminated
//            From HashMap create the priority queue of Ids based on the process priority
            createIdAndBurstTimeMap();
            createPriorityQueueFromMap();
        }

        if (OperatingSystem.isRoundRobinMethod()) {
//             No need to create additional data structure since the process is poll off from ready Queue
//            from ProcessWareHouse
            return;
        }
    }

//     take a process from priority Q, take it out of ready Q
    public Process getProcess() {
//        depend on the method, if PQ then pull the id out of PQ, search for that process, execute that process and erase that process
//        if RR, just pull the most current process, execute a while and put it back

        if (OperatingSystem.isPriorityQueueMethod()) {
            Process process = getProcessIfPriorityQueueMethod();
            Utilities.printSubLine("In scheduler: The retrieved process id: " + process.processControlBlock.getId());
            return process;
        }

        if (OperatingSystem.isRoundRobinMethod()) {
            Process process = getProcessIfRoundRobinMethod();
            Utilities.printSubLine("In scheduler: The retrieved process id: " + process.processControlBlock.getId());
            return process;
        }
        return null;
    }

    public Process getProcessIfPriorityQueueMethod() {
        if (priorityQueue.size() > 0) {
            int id = priorityQueue.remove();
            Process process = this.processWareHouse.searchProcessById(id);
            if (process != null) {
                return process;
            } else {
                return null;
            }
        } else {
            Utilities.print("Priority Queue is empty");
            return null;
        }
    }

    public Process getProcessIfRoundRobinMethod() {
        Process process = null;
        try {
            if (this.processWareHouse.isQueueEmpty(this.processWareHouse.readyQueue)) {
                Utilities.print("The ready queue is empty");
                return null;
            }
            process = this.processWareHouse.getMostCurrentProcessFromReadyQueue();
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
        return process;
    }
    // end


    // create the hashmap to later on feed to priority queue
    public void createIdAndBurstTimeMap() {
        Collection readyQueue = this.processWareHouse.getReadyQueue();
        for (Object process : readyQueue) {
            addProcessToMap((Process) process);
        }
        Utilities.printSubLine("In Scheduler: Create HashMap for PQ");
        printHashMap();
    }


    public boolean addProcessToMap(Process process) {
        try {
            // Very crucial condition check to seperate the 2 thread
            // If specify same properties, one thread can know and change data of another thread
            if (process.processControlBlock.getId() > 0 || process.processControlBlock.getPriority() != 0) {
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
         for (int id : priorityQueue) {
            Utilities.print("The process id is: " + id);
        }
    }

    public void printHashMap() {
        Iterator itr = idAndBurstTimeMap.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry element = (Map.Entry) itr.next();
            Utilities.print("id: " + element.getKey() + " Priority: " + element.getValue());
        }
    }
}
