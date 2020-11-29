package com.company;

public class RRThread implements Runnable {
    Thread t;

    public RRThread() {
        Utilities.printHeadLine("In RR thread");
        t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {
        try{
            Utilities.printHeadLine("Start the OS");
            OperatingSystem os = new OperatingSystem();

//        Change the method here
//        OperatingSystem.setMethod("PQ");
            OperatingSystem.setMethod("RR");

//        create a process
            ProcessControlBlock pcb3 = new ProcessControlBlock();
            pcb3.setId(3);
//            pcb3.setPriority(6);
            pcb3.setBurstTime(5);
            Process time = new CalculateProcess(pcb3);

            ProcessControlBlock pcb4 = new ProcessControlBlock();
            pcb4.setId(4);
//            pcb4.setPriority(1);
            pcb4.setBurstTime(3);
            Process time2 = new CalculateProcess(pcb4);

//        ProcessControlBlock pcb3 = new ProcessControlBlock();
//        pcb3.setId(3);
//        pcb3.setPriority(100);
//        pcb3.setBurstTime(3);
//        Process time3 = new TimerProcess(pcb3, 2);

//        add it to the os
            os.addNewProcess(time);
            os.addNewProcess(time2);
//        os.addNewProcess(time3);
//        os.addNewProcess(time3);
            os.start();
        } catch (Exception e) {
            Utilities.printErr(e.getMessage());
        }
    }
}
