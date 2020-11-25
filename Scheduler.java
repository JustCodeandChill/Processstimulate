package com.company;

import java.util.*;

public class Scheduler {
    Queue<Integer> priorityQueue;
    Map<Integer, Integer> idAndBurstTimeMap;

    public Scheduler() {
        this.priorityQueue = new LinkedList();
        this.idAndBurstTimeMap = new HashMap();
    }


    public boolean addProcessToMap(Process process) {
        try {
            if (Utilities.isValidProcess(process)) {
                idAndBurstTimeMap.put(process.processControlBlock.getId(), process.processControlBlock.getBurstTime());
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

    public boolean removeProcessFromMap(Process process) {
        try {
            int id = process.processControlBlock.getId();
            if (idAndBurstTimeMap.containsKey(id))
                idAndBurstTimeMap.remove(id);
            return true;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
            return false;
        }
    }

    public boolean addProcessToPriorityQueue(Process process) {
        try {
            if (Utilities.isValidProcess(process)) {
                priorityQueue.offer(process.processControlBlock.getId());
            }
            return true;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
            return false;
        }
    }

    public boolean addProcessIdToPriorityQueue(int id) {
        try {
            priorityQueue.offer(id);
            return true;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
            return false;
        }
    }

    public List<Integer> getSortedProcessListByBurstTime() {
        List<Integer> processByBurstTime = new ArrayList<>(idAndBurstTimeMap.values());
        Collections.sort(processByBurstTime);
        return processByBurstTime;
    }

    public boolean createPriorityQueue(List<Integer> processIdList) {
        for (int id : processIdList) {
            if (!addProcessIdToPriorityQueue(id))
                return false;
        }
        return true;
    }
}
