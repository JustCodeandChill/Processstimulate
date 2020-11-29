package com.company;

public class Main {

    public static void main(String[] args) {
        Utilities.print("new thread");
        new PQThread();
        new RRThread();
    }
}
