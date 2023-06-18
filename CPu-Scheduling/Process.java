//Asala Ehab Mohmmed        20201020
//Dina Othman Emam			20200173
//Habiba Ayman El-tahry		20200140
//Rana Ashraf				20201067
package ProjectPackage;

public class Process 
{
	
	public String name;
    public int arrivalTime;
    public int burstTime;
    public int burstTime2;
    public int waitingTime;
    public int turnAroundTime;
    public int completionTime;
    public int responseTime;
    public int remaining;
    public int priority;
    public int times=1;
    public  int fi_time=0;
    public int quantum;

    public int reminingQ;

    public int order;

    public int update;
    public Process(){}

    public Process(String name, int arrivalTime,int burstTime)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.responseTime = -1; //Time at which the process gets the CPU for the first time - Arrival time
        this.remaining = burstTime;
        this.burstTime2=burstTime;
    }
    
    public Process(String name, int arrivalTime, int burstTime,int priority)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority=priority;

    }
    public Process(String name, int arrivalTime, int burstTime,int priority,int quantum)
    {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority=priority;
        this.quantum=quantum;
        this.burstTime2=burstTime;
        this.reminingQ=quantum;

    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public int getArrivalTime() 
    {
        return arrivalTime;
    }
    
    public int getPriority() 
    {
        return priority;
    }
    public int getQuantum(){return quantum;}

    @Override
    public String toString() 
    {
        return name + " :  " + " WaitingTime=" + waitingTime + ",  turnaroundTime=" + turnAroundTime;
    }
	
	

}
