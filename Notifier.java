package com.company;
import java.util.Timer;
import java.util.TimerTask;

public class Notifier {
    Timer timer;
    long endTime = System.currentTimeMillis() + 1000;

    public Notifier() {
        timer = new Timer();
    }

    public void execute(int seconds) {
//        timer.schedule(new RemindTask(), seconds*1000); // schedule the task

        while (System.currentTimeMillis() < endTime) {
            System.out.println("Executing timer processs" + System.currentTimeMillis());
        }

        Utilities.print("End of message");
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("");
            timer.cancel(); //Terminate the timer thread
        }
    }
}
