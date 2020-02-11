/*
Name: Barath Tirumala 
Course: CNT 4714 Spring 2020  
Assignment title: Project 2 – Multi-threaded programming in Java  
Date:  February 9, 2020 
Class:  Station
*/

public class Station implements Runnable{
    int workload;
    int numStations;
    Conveyor input;
    Conveyor output;
    int ID;
    int InpID;
    int OutID;

    public Station(int workload, int ID, int numStations){
        this.workload = workload;
        this.ID = ID;
        this.numStations = numStations;

        this.InpID = ID;
        this.OutID = ID == 0 ? numStations - 1 : ID - 1;
    }

    public void setInput(Conveyor inp){
        this.input = inp;
    }

    public void setOutput(Conveyor out){
        this.output = out;
    }

    @Override  
    public void run() {
        System.out.println("Station " + this.ID + ": Workload set. Station " + this.ID + " has " + this.workload + " package groups to move");
        System.out.println("Station " + this.ID + ": In-Connection set to conveyer " + this.InpID);
        System.out.println("Station " + this.ID + ": Out-Connection set to conveyer " + this.OutID);

        while(this.workload != 0){
            
            // try to get input lock
            if(input.lock.tryLock()){
                // success to input lock
                System.out.println("Station " + this.ID + ": holds lock on (granted access) to conveyer " + this.InpID);

                //try output lock
                if(output.lock.tryLock()){
                    System.out.println("Station " + this.ID + ": holds lock on (granted access) to conveyer " + this.OutID);

                    // got both locks, eat
                    System.out.println("Station " + this.ID + ": successfully moves packages on conveyer " + this.input.ID);
                    System.out.println("Station " + this.ID + ": successfully moves packages on conveyer " + this.output.ID);
                    this.workload --;
                    System.out.println("Station " + this.ID + ": has " + this.workload + " package groups left to move");
                }
                else{
                    System.out.println("Station " + this.ID + ": unlocks (released access) to conveyer " + this.InpID);
                    input.lock.unlock();
                }

                // release input
                if(input.lock.isHeldByCurrentThread()){
                    System.out.println("Station " + this.ID + ": unlocks (released access) to conveyer " + this.InpID);
                    input.lock.unlock();
                }

                // release output
                if(output.lock.isHeldByCurrentThread()){
                    System.out.println("Station " + this.ID + ": unlocks (released access) to conveyer " + this.OutID);
                    output.lock.unlock();
                }

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }
        System.out.println("* * Station " + this.ID + ": Workload successfully completed. * *");
    }
}