package com.company;

import java.util.Collection;
import java.util.Iterator;
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
    // OS
    OperatingSystem osController;

    public ProcessWareHouse(OperatingSystem os) {
        this.jobQueue = new LinkedList<>();
        this.readyQueue = new LinkedList<>();
        this.waitQueue = new LinkedList<>();
        this.blockQueue = new LinkedList<>();
        this.terminateQueue = new LinkedList<>();
        this.readyQueueInLinkedList = new LinkedList<>();
        this.osController = os;
        Utilities.print("" + Utilities.getMethod());
    }

    public void connectToDispatcher(Dispatcher dispatcher) {
        try {
            this.dispatcher = dispatcher;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

//  Based on the method PQ or RR, the data structure will be different
    public Collection<Process> getReadyQueue() {
        if (OperatingSystem.isPriorityQueueMethod()) { return getReadyQueueIfPriorityQueue(); }
        if (OperatingSystem.isRoundRobinMethod()) { return getReadyQueueIfRoundRobin(); }
        return null;
    }
    public Queue<Process> getReadyQueueIfRoundRobin() { return this.readyQueue; }

    public LinkedList<Process> getReadyQueueIfPriorityQueue() { return this.readyQueueInLinkedList; }
//  End

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
    public void addProcessToReadyQueueInLinkedList(Process process) {
        try {

            Utilities.print("In PWH: Add most current process to ready queue linked list");
            this.readyQueueInLinkedList.add(process);
            this.osController.changeProcessStateToReady(process);
            Utilities.printBreakLine();

        } catch (Exception e) {
            Utilities.print("Error happenned: " + e.getMessage());
        }
    }

    public void removeProcessFromReadyQueueInLinkedListById(int id) {
        try {
            if (id <= 0) throw new IllegalArgumentException("Invalid id");
            Process process = searchProcessById(id);
            this.readyQueueInLinkedList.remove(process);
            Utilities.print("In PWH: remove a process with id: " + id + " out of Ready Queue.");
        } catch (Exception e) {
            Utilities.print("Error happenned: " + e.getMessage());
        }
    }

    // End Priority Queue implementation


    //Round robin implementation
    public void addProcessToReadyQueue(Process process) {
        try {
            Utilities.print("Add most current process to ready queue");
            osController.changeProcessStateToReady(process);
            if (OperatingSystem.isPriorityQueueMethod()) {
                this.readyQueueInLinkedList.add(process);
            }
            else if (OperatingSystem.isRoundRobinMethod()) {
                this.readyQueue.add(process);
            }
            Utilities.printBreakLine();
        } catch (Exception e) {
            Utilities.print("Error happenned: " + e.getMessage());
        }
    }

    public Process removeMostCurrentProcessFromReadyQueue() {

//        if (OperatingSystem.isPriorityQueueMethod()) {
//            if (!isQueueEmpty(readyQueueInLinkedList)) {
//                Utilities.print("Remove most current process from ready queue linkedlist");
//                Process process = searchProcessById(id);
//                Utilities.print("The poll off process is id " + process.processControlBlock.getId());
//                return process;
//            }
//        }
         if (OperatingSystem.isRoundRobinMethod()) {
            if (!isQueueEmpty(readyQueue)) {
                Utilities.print("Remove most current process from ready queue");
                Process process = this.readyQueue.poll();
                Utilities.print("The poll off process is id " + process.processControlBlock.getId());
                return process;
            }
        }
        return null;
    }
    // End of round robin implementation

//     Utilities
//    To use in readyQueueLinkedList
    public Process searchProcessById(int id) {
        Iterator<Process> itr = this.readyQueueInLinkedList.iterator();

        while (itr.hasNext()) {
            Process currentProcess = itr.next();
            int currentId = currentProcess.processControlBlock.getId();
            if (currentId == id) return currentProcess;
        }
        return null;
    }

//    to check the existence of a process in system
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
