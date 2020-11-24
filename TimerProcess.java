package com.company;

public class TimerProcess extends Process{
    Notifier notifier;
    int second;

    public TimerProcess(ProcessControlBlock processControlBlock, int second) {
        this.processControlBlock = processControlBlock;
        this.notifier = new Notifier();
        this.second = second;
        Utilities.print("A new timer process is created");
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        Utilities.print("Timer process id:" + processControlBlock.getId()
                + " is set to " + second + " second");
        this.second = second;
    }

    @Override
    public void toExecute() {
        Utilities.print("Timer process id:" + processControlBlock.getId()
                + " is being executed");
        notifier.execute(second);
    }

    @Override
    public void toTerminate() {
        Utilities.print("Timer process id:" + processControlBlock.getId()
                + " is terminated");
    }

    @Override
    public void toWait() {

    }

    @Override
    public void toBlocked() {

    }
}
