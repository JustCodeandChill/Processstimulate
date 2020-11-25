package com.company;

public class Utilities {
    static void print(String message) {
        System.out.println(message);
    }
    static void printErr(String message)  {
        System.out.println("Error happened: " + message);
    }
    static boolean isValidProcess(Process process) {return process.processControlBlock.getId() > 0;}
}
