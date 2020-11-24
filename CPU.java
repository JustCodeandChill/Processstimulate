package com.company;

public class CPU {
    Process currentProcess;

    public CPU() {
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;
        Utilities.print("Process id: " + currentProcess.processControlBlock.getId()
                + " is set to the CPU to execute");
    }

    public void toExecute() {
        this.currentProcess.toExecute();
    }

    public void toExecute(Process process) {
        process.toExecute();
    }
}
