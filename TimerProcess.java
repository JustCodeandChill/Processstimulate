package com.company;

import com.company.Notifier;
import com.company.Process;
import com.company.ProcessControlBlock;
import com.company.Utilities;

public class TimerProcess extends Process {
    Notifier notifier;
    int second;

    public TimerProcess(ProcessControlBlock processControlBlock, int second) {
        this.processControlBlock = processControlBlock;
        this.notifier = new Notifier();
        this.second = second;
        Utilities.print("A new timer process with id: " + processControlBlock.getId() + " is created");
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
        notifier.toExecute(second);
    }

    @Override
    public void toTerminate() {
        Utilities.print("Timer process id:" + processControlBlock.getId()
                + " is terminated");
        notifier.toTerminate();
    }

    @Override
    public void toWait() {
        Utilities.print("Timer process id:" + processControlBlock.getId()
                + " is put on wait");
        notifier.contextSwitch();
    }

    @Override
    public void toBlocked() {

    }
}
