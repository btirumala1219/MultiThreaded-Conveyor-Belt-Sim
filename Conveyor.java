/*
Name: Barath Tirumala 
Course: CNT 4714 Spring 2020  
Assignment title: Project 2 – Multi-threaded programming in Java  
Date:  February 9, 2020 
Class:  Conveyor
*/

import java.util.concurrent.locks.ReentrantLock;

public class Conveyor {
    public int ID;
    public ReentrantLock lock = new ReentrantLock();
    boolean inUse = false;

    public Conveyor( int ID ){
        this.ID = ID;
    }
}