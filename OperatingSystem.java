package com.company;

public class OperatingSystem {
    ProcessWareHouse pool;
    Scheduler scheduler;
    Dispatcher dispatcher;
    CPU cpu;
    public static boolean isExecutingAProcess;
    public static String method = "PQ";

    public OperatingSystem() {
        this.pool = new ProcessWareHouse();
        this.scheduler = new Scheduler();
        this.dispatcher = new Dispatcher();
        this.cpu = new CPU();
        this.isExecutingAProcess = false;
    }

    public OperatingSystem(ProcessWareHouse pool, Scheduler scheduler, Dispatcher dispatcher, CPU cpu) {
        this.pool = pool;
        this.scheduler = scheduler;
        this.dispatcher = dispatcher;
        this.cpu = cpu;
    }

    // main functionality
    public void start() {
        this.scheduler.connectToProcessWareHouse(this.pool);
        this.scheduler.connectToDispatcher(this.dispatcher);
        this.pool.connectToDispatcher(this.dispatcher);
        Utilities.printHeadLine("start scheduler");
        this.scheduler.start();
        Utilities.print("print queue");
        this.scheduler.printPriorityQueue();
        Utilities.printHeadLine("start dispatcher");
        Utilities.print("Connect dispatcher");
        this.dispatcher.connectToScheduler(this.scheduler);
        this.dispatcher.connectToProcessWareHouse(this.pool);

        while (!this.pool.readyQueue.isEmpty()) {
            this.dispatcher.start();
        }
    }

    // new process will be added to job pool then move to ready queue
    public void addNewProcess(Process process) {
        try {
            Utilities.printHeadLine("A new process is created in the OS");
            this.pool.addProcessToJobQueue(process);
            this.pool.removeMostCurrentProcessFromJobQueue();
            this.pool.addProcessToReadyQueue(process);
        }catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }
    // end of main functionalities

    // Utilities
    public boolean isIsExecutingAProcess() {return isExecutingAProcess;}
    
    public ProcessWareHouse getPool() {
        return pool;
    }

    public void setPool(ProcessWareHouse pool) {
        this.pool = pool;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public boolean isExecutingAProcess() {
        return isExecutingAProcess;
    }

    public void setExecutingAProcess(boolean executingAProcess) {
        isExecutingAProcess = executingAProcess;
    }


}
