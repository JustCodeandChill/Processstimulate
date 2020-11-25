package com.company;
// will take a process from scheduler and change state and assign to cpu
public class Dispatcher {
    Scheduler scheduler;
    CPU cpu;

    public Dispatcher() {
        this.cpu = new CPU();
    }
    public void start() {
        this.getProcess();
    }

    public void connectToScheduler(Scheduler scheduler) {
        try {
            this.scheduler = scheduler;
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }

    public void getProcess() {
        Process process = scheduler.getProcess();
        Utilities.print("process id: " + process.processControlBlock.getId() +
                "- priority" + process.processControlBlock.getPriority());
        this.cpu.setCurrentProcess(process);
        this.cpu.toExecute();
    }
}
