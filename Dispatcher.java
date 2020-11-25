package com.company;

// will take a process from scheduler and change state and assign to cpu
public class Dispatcher {
    Scheduler scheduler;
    ProcessWareHouse processWareHouse;
    CPU cpu;

    public Dispatcher() {
        //dispatcher feed process directly to cpu and force it to execute current process
        this.cpu = new CPU();
    }

    public void start() {
        Process process = this.getProcessFromScheduler();
        //change state of this process to execute;
        this.processWareHouse.removeMostCurrentProcessFromReadyQueue();
        //move this to exit state
        Utilities.print("process id: " + process.processControlBlock.getId() +
                "- priority" + process.processControlBlock.getPriority());
        // execute the process
        changeStateToRun(process);
        this.cpu.setCurrentProcess(process);
        this.cpu.toExecute();
        // move process to completed
        changeStateToCompleted(process);
    }

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

    // Change state functionality
    public void changeStateToRun(Process process) {
        try {
            Utilities.print("Change the process with id " +
                    process.processControlBlock.getId() + " to state " + process.processControlBlock.getState());
            process.processControlBlock.setState("Run");
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void changeStateToReady(Process process) {
        try {
            Utilities.print("Change the process with id " +
                    process.processControlBlock.getId() + " to state " + process.processControlBlock.getState());
            process.processControlBlock.setState("Ready");
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void changeStateToCompleted(Process process) {
        try {
            Utilities.print("Change the process with id " +
                    process.processControlBlock.getId() + " to state " + process.processControlBlock.getState());
            process.processControlBlock.setState("Completed");
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    //end change state functions
}


