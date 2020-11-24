package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Scheduler {
    Queue<Integer> priorityQueue;
    Map<Integer,Integer> idAndBurstTimeMap;

    public Scheduler(Queue priorityQueue) {
        this.priorityQueue = new LinkedList();
        this.idAndBurstTimeMap = new HashMap();
    }

    public boolean addProcess(Process process) {
        //based on burst time, predict priority


        // if the highest, put it in the first

        // if the lowest put in the last

        return true;
    }

    public void addProcessToMap(Process process) {
        try {
            if (Utilities.isValidProcess(process))
                idAndBurstTimeMap.put(process.processControlBlock.getId(), process.processControlBlock.getBurstTime());
            else
                Utilities.printErr("Cannot add to map");
        }catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void removeProcessFromMap(Process process) {
        try {
            int id = process.processControlBlock.getId();
            if (idAndBurstTimeMap.containsKey(id))
                idAndBurstTimeMap.remove(id);
        }catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public boolean addProcessToPriorityQueue(Process process) {
        try {

        }catch (Exception e){

        }
    }
}
