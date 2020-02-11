/*
Name: Barath Tirumala 
Course: CNT 4714 Spring 2020  
Assignment title: Project 2 – Multi-threaded programming in Java  
Date:  February 9, 2020 
Class:  Main
*/

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String file = "config.txt";

        File inputFile = new File(file);
        Scanner scanner = new Scanner(inputFile);

        int numStations = scanner.nextInt();
        int[] workloads = new int[numStations];
        Conveyor[] conveyors = new Conveyor[numStations];

        for(int i = 0; i<numStations; i++){
            workloads[i] = scanner.nextInt();
            conveyors[i] = new Conveyor(i);
        }

        scanner.close();

        Thread[] threads = new Thread[numStations];

        for(int i = 0; i<numStations; i++){
            Station curStat = new Station(workloads[i], i, numStations);
            curStat.setInput(conveyors[curStat.InpID]);
            curStat.setOutput(conveyors[curStat.OutID]);

            threads[i] = new Thread(curStat);
            threads[i].start();
        }
        
    }


}