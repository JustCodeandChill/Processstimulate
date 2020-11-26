package com.company;

import java.util.Queue;
import java.util.LinkedList;

public class ProcessWareHouse<V> {
    Queue<Process> jobQueue;
    // use readyQue in roundrobin
    Queue<Process> readyQueue;

    //use LinkedQueue in priorityQ
    LinkedList<Process> readyQueueInLinkedList;

    Queue<Process> waitQueue;
    Queue<Process> blockQueue;
    Queue<Process> terminateQueue;
    Dispatcher dispatcher;

    public ProcessWareHouse() {
        this.jobQueue = new LinkedList<>();
        this.readyQueue = new LinkedList<>();
        this.waitQueue = new LinkedList<>();
        this.blockQueue = new LinkedList<>();
        this.terminateQueue = new LinkedList<>();
        this.readyQueueInLinkedList = new LinkedList<>();
        Utilities.print("" + Utilities.getMethod());
    }

    public void connectToDispatcher(Dispatcher dispatcher) {
        try {
            this.dispatcher = dispatcher;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    // Job queue functionalities
    public boolean addProcessToJobQueue(Process process) {
        try {
            Utilities.print("Add most current process to job queue");
            this.jobQueue.add(process);
            return true;
        } catch (Exception e) {
            Utilities.print("Error happenned: " + e.getMessage());
            return false;
        }
    }

    public Process removeMostCurrentProcessFromJobQueue() {
        try {
            Utilities.print("Remove most current process from job queue");
            if (!isQueueEmpty(jobQueue)) {
                Process process = this.jobQueue.remove();
                return process;
            } else
                return null;
        } catch (Exception e) {
            Utilities.print("Error happenned: " + e.getMessage());
            return null;
        }
    }
    // End job queue functionality

    // Prioriy Queue implementation


    // Priority Queue implementation

    //Round robin implementation
    public boolean addProcessToReadyQueue(Process process) {
        try {
            Utilities.print("Add most current process to ready queue");
            this.readyQueue.add(process);
            Utilities.printBreakLine();
            return true;
        } catch (Exception e) {
            Utilities.print("Error happenned: " + e.getMessage());
            return false;
        }
    }

    public Process removeMostCurrentProcessFromReadyQueue() {
        Utilities.print("Remove most current process from ready queue");
        if (!isQueueEmpty(readyQueue)) {
            Process process = this.readyQueue.poll();
            Utilities.print("The poll off process is id " + process.processControlBlock.getId());
            return process;
        } else {
            return null;
        }
    }
    // End of round robin implementation

    // Utilities to check the existence of a process in system
    public boolean isProcessInWareHouse(Process process) {
        return (isInJobQueue(process) || isInReadyQueue(process) || isInBlockQueue(process)
                || isInTerminateQueue(process) || isInWaitQueue(process));
    }

    public Queue<Process> getQueueOfCurrentProcess(Process process) {
        if (isInReadyQueue(process)) return readyQueue;
        if (isInWaitQueue(process)) return waitQueue;
        if (isInJobQueue(process)) return jobQueue;
        if (isInBlockQueue(process)) return blockQueue;
        if (isInTerminateQueue(process)) return terminateQueue;
        return null;
    }

    public boolean isInJobQueue(Process process) {
        return jobQueue.contains(process);
    }

    public boolean isInReadyQueue(Process process) {
        return readyQueue.contains(process);
    }

    public boolean isInWaitQueue(Process process) {
        return waitQueue.contains(process);
    }

    public boolean isInTerminateQueue(Process process) {
        return terminateQueue.contains(process);
    }

    public boolean isInBlockQueue(Process process) {
        return blockQueue.contains(process);
    }

    public boolean isQueueEmpty(Queue queue) {
        return queue.isEmpty();
    }
    // End utilities

//    public boolean removeProcessFromQueue(Process process) {
//        try {
//            Queue<Process> currentQueue = getQueueOfCurrentProcess(process);
//            removeProcess(currentQueue, process);
//            return true;
//        } catch (Exception e) {
//            Utilities.print("Error happended: " + e.getMessage());
//            return false;
//        }
//    }
//
//    public boolean removeProcess(Queue<Process> currentQueue, Process process) {
//        try {
//            currentQueue.remove(process);
//            return true;
//        } catch (Exception e) {
//            Utilities.print("Error happended: " + e.getMessage());
//            return false;
//        }
//    }
//
//    public boolean moveProcessToReadyQueue(Process process) {
//        try {
//            if (isProcessInWareHouse(process)) {
//                removeProcessFromQueue(process);
//            }
//            this.readyQueue.add(process);
//            return true;
//        } catch (Exception e) {
//            Utilities.print("Error happenned: " + e.getMessage());
//            return false;
//        }
//    }


}
