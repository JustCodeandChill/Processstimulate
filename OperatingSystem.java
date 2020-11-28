package com.company;

import java.util.Collection;

public class OperatingSystem {
    ProcessWareHouse pool;
    Scheduler scheduler;
    Dispatcher dispatcher;
    CPU cpu;
    public boolean allWorksAreDone;

    public static boolean isExecutingAProcess() {
        return isExecutingAProcess;
    }

    public static void setIsExecutingAProcess(boolean isExecutingAProcess) {
        OperatingSystem.isExecutingAProcess = isExecutingAProcess;
    }

    public static boolean isExecutingAProcess;

    public static int getIdCurrentExecutingProcess() {
        return idCurrentExecutingProcess;
    }

    public static void setIdCurrentExecutingProcess(int idCurrentExecutingProcess) {
        OperatingSystem.idCurrentExecutingProcess = idCurrentExecutingProcess;
    }

    public static int idCurrentExecutingProcess;

    public static void setMethod(String method) {
        OperatingSystem.method = method;
    }

    public static String method = "RR";


    public OperatingSystem() {
        this.pool = new ProcessWareHouse(this);
        this.scheduler = new Scheduler(this);
        this.dispatcher = new Dispatcher(this);
        this.cpu = new CPU();
    }

    public OperatingSystem(ProcessWareHouse pool, Scheduler scheduler, Dispatcher dispatcher, CPU cpu) {
        this.pool = pool;
        this.scheduler = scheduler;
        this.dispatcher = dispatcher;
        this.cpu = cpu;
    }

    public OperatingSystem getOSController() {
        return this;
    }

    public void allWorksAreDone() {
        allWorksAreDone = true;
    }

    // main functionality
    public void start() {
        //connect every instances together
        this.scheduler.connectToProcessWareHouse(this.pool);
        this.scheduler.connectToDispatcher(this.dispatcher);
        this.pool.connectToDispatcher(this.dispatcher);

        // scheduler will pick a process, based on the method
        Utilities.printHeadLine("start scheduler");
        this.scheduler.start();
        Utilities.print("print queue");
        this.scheduler.printPriorityQueue();
        Utilities.printHeadLine("start dispatcher");
        Utilities.printHeadLine("Connect dispatcher");
        this.dispatcher.connectToScheduler(this.scheduler);
        this.dispatcher.connectToProcessWareHouse(this.pool);

        Collection readyQueue = this.pool.getReadyQueue();
        while (!readyQueue.isEmpty()) {
//            if (allWorksAreDone) {
//                Utilities.printHeadLine("All over");
//                return;
//            }
            this.dispatcher.start();
        }
    }

    // new process will be added to job pool then move to ready queue
    public void addNewProcess(Process process) {
        try {
            Utilities.printHeadLine("A new process is created in the OS");
            if (isPriorityQueueMethod()) {
                this.pool.addProcessToJobQueue(process);
                this.pool.removeMostCurrentProcessFromJobQueue();
                this.pool.addProcessToReadyQueue(process);
            }

            if (isRoundRobinMethod()) {
                this.pool.addProcessToJobQueue(process);
                this.pool.removeMostCurrentProcessFromJobQueue();
                this.pool.addProcessToReadyQueue(process);
            }
        }catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }
    // end of main functionalities

    // Utilities
    static boolean isPriorityQueueMethod() {return  OperatingSystem.method.equals("PQ");}

    static boolean isRoundRobinMethod() {return  OperatingSystem.method.equals("RR");}

    static String getMethod() {return OperatingSystem.method;}


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

    //Actions
    public void changeProcessStateToReady(Process process) {
        this.dispatcher.changeStateToReady(process);
    }
}
