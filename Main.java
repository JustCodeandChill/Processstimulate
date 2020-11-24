package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Start the OS");
        ProcessControlBlock pcb1 = new ProcessControlBlock();
        pcb1.setId(1);
        pcb1.setPriority(2);
        Process time = new TimerProcess(pcb1, 2);
        CPU cpu = new CPU();
        cpu.setCurrentProcess(time);
        cpu.toExecute();
    }
}
