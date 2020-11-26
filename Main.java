package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Start the OS");
//        CPU cpu = new CPU();
//        cpu.setCurrentProcess(time);
//        cpu.toExecute();

        OperatingSystem os = new OperatingSystem();
//        create a process
        ProcessControlBlock pcb1 = new ProcessControlBlock();
        pcb1.setId(100);
        pcb1.setPriority(6);
        Process time = new CalculateProcess(pcb1);

        ProcessControlBlock pcb2 = new ProcessControlBlock();
        pcb2.setId(2);
        pcb2.setPriority(1);
        Process time2 = new TimerProcess(pcb2, 2);
//
//        ProcessControlBlock pcb3 = new ProcessControlBlock();
//        pcb3.setId(3);
//        pcb3.setPriority(100);
//        Process time3 = new TimerProcess(pcb3, 2);

        // add it to the os
        os.addNewProcess(time);
//        os.addNewProcess(time2);
//        os.addNewProcess(time3);
        os.start();
    }
}
