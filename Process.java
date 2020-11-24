package com.company;

public abstract class Process {
    ProcessControlBlock processControlBlock;
    public abstract void toExecute();
    public abstract void toTerminate();
    public abstract void toWait();
    public abstract void toBlocked();
    public Process getProcessById(int id) {
        if (this.processControlBlock.getId() == id) return this;
        return null;
    }
}
