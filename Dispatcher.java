package com.company;

import javax.management.OperationsException;

// will take a process from scheduler and change state and assign to cpu
public class Dispatcher {
    Scheduler scheduler;
    ProcessWareHouse processWareHouse;
    CPU cpu;
    String method;
    OperatingSystem osController;

    public Dispatcher(OperatingSystem osController) {
        //dispatcher feed process directly to cpu and force it to execute current process
        this.cpu = new CPU();
        this.osController = osController;
        this.method = OperatingSystem.getMethod();
    }

    //main functionality
    public void start() {
        if (OperatingSystem.isPriorityQueueMethod()) {
            startWithPriorityQueueMethod();
        }

        if (OperatingSystem.isRoundRobinMethod()) {
            startWithRoundRobinMethod();
        }
    }

    public void startWithPriorityQueueMethod() {
        try {
            if (!OperatingSystem.isExecutingAProcess()) {
                Process process = this.getProcessFromScheduler();
                OperatingSystem.setIsExecutingAProcess(true);
    //        change state of this process to execute;
    //        this one need to be changed, the data stucute is not correct for priority queue
//            this.processWareHouse.removeMostCurrentProcessFromReadyQueue();
                this.processWareHouse.removeProcessFromReadyQueueInLinkedListById(process.processControlBlock.getId());
                //move this to ready state
                Utilities.print("In Dispatcher: process id: " + process.processControlBlock.getId() +
                        "- priority: " + process.processControlBlock.getPriority());
                //move to run state and execute the process
                changeStateToRun(process);
                OperatingSystem.setIsExecutingAProcess(true);
                this.cpu.setCurrentProcess(process);
                this.cpu.toExecute();
                // move process to completed
            if (!OperatingSystem.isExecutingAProcess())
                changeStateToCompleted(process);
            }

        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void startWithRoundRobinMethod() {

    }
    // end main functionality

    // connect with other component
    public void connectToScheduler(Scheduler scheduler) {
        try {
            this.scheduler = scheduler;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void connectToProcessWareHouse(ProcessWareHouse processWareHouse) {
        try {
            this.processWareHouse = processWareHouse;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public Process getProcessFromScheduler() {
        Process process = scheduler.getProcess();
        return process;
    }
    // end of connection function

    // Change state functionality
    public void changeStateToNew(Process process) {
        try {
            process.processControlBlock.setState("New");
            Utilities.printHeadLine("In Dispatcher: Change the process with id " +
                    process.processControlBlock.getId() + " to state " + process.processControlBlock.getState());
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void changeStateToRun(Process process) {
        try {
            process.processControlBlock.setState("Run");
            Utilities.printHeadLine("In Dispatcher: Change the process with id " +
                    process.processControlBlock.getId() + " to state " + process.processControlBlock.getState());
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void changeStateToReady(Process process) {
        try {
            process.processControlBlock.setState("Ready");
            Utilities.printHeadLine("In Dispatcher: Change the process with id " +
                    process.processControlBlock.getId() + " to state " + process.processControlBlock.getState());
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void changeStateToCompleted(Process process) {
        try {
            process.processControlBlock.setState("Completed");
            Utilities.printHeadLine("In Dispatcher: Change the process with id " +
                    process.processControlBlock.getId() + " to state " + process.processControlBlock.getState());
            Utilities.printBreakLine();
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    //end change state functions
}


