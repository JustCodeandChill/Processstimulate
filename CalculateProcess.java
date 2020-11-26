package com.company;

public class CalculateProcess extends Process {
//    ProcessControlBlock processControlBlock;

    public CalculateProcess(ProcessControlBlock processControlBlock) {
        this.processControlBlock = processControlBlock;
        Utilities.print("A new calculate process with id:" + this.processControlBlock.getId() + " is created.");
    }

    @Override
    public void toExecute() {
        Utilities.print("Calcualte process id:" + this.processControlBlock.getId()
                + " is being executed");
        Utilities.print("The matrix derivation is 42");
        Utilities.print("The matrix abatros is 190");
    }

    @Override
    public void toTerminate() {
        Utilities.print("Calcualte process id:" + this.processControlBlock.getId()
                + " is terminated");
    }

    @Override
    public void toWait() {

    }

    @Override
    public void toBlocked() {

    }
}
