# Processstimulate
Problem statement: 
Simulate the following process of the kernel of an O.S :
- Dispatching, scheduling transitioning
- Dispatching following a round robin technique and priority
- Change the status of the process accordingly


## Assumption
-	All processes are arrived at time 0, before being fed into the system. 
-	The OS is fairly simple: It will not deal with I/O process that require user interaction, time delay. Thus, no I/O process will appear in the system. The provided process is simple, it just prints out some text.
-	The system in question 1 only demonstrate a simple interacting flow between components of the operating system depends on the methods (Round Robin or Priority Queue).

## Main functionalities

In 4 classes: OperatingSystem.java, ProcessWareHouse.java, Scheduler.java, Dispatcher.java, they have the start() method that will be called to do that component specific missions (storing, scheduling, picking process). The CPU.java has the toExecute() method that execute the given process.

## Basic Structure
There are 4 main components in this stimulation namely ProcessWareHouse (in ProcessWareHouse.java – similar term used: PWH), Scheduler (in Scheduler.java), Dispatcher (in Dispatcher.java) and CPU (in CPU.java). They interact in Operating System class (in OperatingSystem.java). Each has different purposes and interleave with each other.

## Datagram
![image](https://user-images.githubusercontent.com/43172690/109396038-45e6b400-78f5-11eb-9273-a80f2946de05.png)
