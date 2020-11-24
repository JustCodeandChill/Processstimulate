package com.company;

public class CalculateProcess extends Process {
    ProcessControlBlock processControlBlock;

    public CalculateProcess(ProcessControlBlock processControlBlock) {
        Utilities.print("A new calculate process with id:" + processControlBlock.getId() + " is created.");
    }

    @Override
    public void toExecute() {
        Utilities.print("Calcualte process id:" + processControlBlock.getId()
                + " is being executed");
        Utilities.print("The matrix derivation is 42");
    }

    @Override
    public void toTerminate() {
        Utilities.print("Calcualte process id:" + processControlBlock.getId()
                + " is terminated");
    }

    @Override
    public void toWait() {

    }

    @Override
    public void toBlocked() {

    }
}
