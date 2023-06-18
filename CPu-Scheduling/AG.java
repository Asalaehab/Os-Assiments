//Asala Ehab Mohmmed        20201020
//Dina Othman Emam			20200173
//Habiba Ayman El-tahry		20200140
//Rana Ashraf				20201067

package ProjectPackage;

import javax.lang.model.type.NullType;
import java.util.*;
import java.lang.Math;

public class AG {
    public ArrayList<Process> p;//to hold the process in these array
    public ArrayList<Process> p_copy;
    Queue<Process> ready_queue = new LinkedList<>();
    //Iterator<Process> itr = ready_queue.iterator();


    public AG(ArrayList<Process> pp) {
        this.p = pp;
        this.p_copy=pp;
        Collections.sort(p, Comparator.comparing(Process::getArrivalTime)); //Sorting processes with respect to arrival time
    }


    public  void  calc(){
        System.out.println("calc"+p_copy.size());
        for (int i=0;i<p_copy.size();i++){
            System.out.println("fin "+  p_copy.get(i).fi_time);
            p_copy.get(i).turnAroundTime= p_copy.get(i).fi_time -  p_copy.get(i).arrivalTime;
            p_copy.get(i).waitingTime =  p_copy.get(i).fi_time -  p_copy.get(i).arrivalTime -  p_copy.get(i).burstTime;

        }
        for (int i=0;i<p_copy.size();i++)
        {
            System.out.println("the order of execution  for process : "+p_copy.get(i).order+1);
            System.out.println("waiting time  for process "+p_copy.get(i).name+" = "+p_copy.get(i).waitingTime);
            System.out.println("turnaround time for the process "+p_copy.get(i).name+" = "+p_copy.get(i).turnAroundTime);
            System.out.println("the average waiting time for process "+p_copy.get(i).name +" = "+(p_copy.get(i).waitingTime)/p_copy.size());
            System.out.println("the average turn around time for the process "+p_copy.get(i).name +" = "+(p_copy.get(i).turnAroundTime)/p_copy.size());
            System.out.println("the update for process : "+p_copy.get(i).name+ " is = "+p_copy.get(i).update);
        }
    }

    static void executeAG() {
        System.out.println("Enter number of process:");

        int n = main.in.nextInt();

        ArrayList<Process> processes = new ArrayList<>();

        System.out.print("Enter context switching time: \n");

        int contextTime = main.in.nextInt();


        for (int i = 0; i < n; i++) {
            main.in.nextLine();

            System.out.print("Enter name of process " + (i + 1) + " : \n");
            String name = main.in.nextLine();

            System.out.print("Enter burst time of process " + (i + 1) + " : \n");
            int burstTime = main.in.nextInt();

            System.out.print("Enter arrival time of process " + (i + 1) + " : \n");
            int arrivalTime = main.in.nextInt();

            System.out.print("Enter priority of process " + (i + 1) + ": \n");
            int pn = main.in.nextInt();

            System.out.println("enter the Quantum time : ");
            int quantum = main.in.nextInt();
            processes.add(new Process(name, arrivalTime, burstTime, pn, quantum));

        }
        System.out.println();

        AG ag = new AG(processes);// we call the constructor after adding
        ag.execute();

        System.out.println();
    }

    /**
     * what we will do in these class
     **/
//    private boolean check( ){
//        int counter =0;
//        for (Process process : p_copy) {
//            if (process.burstTime2 == 0) {
//                counter++;
//            }
//        }
//        return (counter == p_copy.size());
//    }
    public void execute()
    {

        int t = 0;
        int s = p.size();
        int counter = 0;
        int i = 0;
        Process current = p.get(i);
        int times = 1;
        while (true)
        {


            if (current.times == 1)
            {
                //check for the busrt time is it has finished
                if (current.burstTime2 == 0)
                {
                    counter++;
                    current.order=counter;//new edit
                    current.times=0;
                    ready_queue.remove(current);
                    current=ready_queue.peek();
                    //check the ready queue
                }
                else
                {

                    if(current.burstTime2<=(int) Math.ceil((double) current.quantum / 4))
                    {
                        current.reminingQ=current.reminingQ-current.burstTime2;//burst time for work >>

                        t += current.burstTime2;
                        System.out.println("time: "+t);
                        current.fi_time=t;
                        current.burstTime2 =0;

                    }
                    else
                    {
                        current.burstTime2 -= (int) Math.ceil((double) current.quantum / 4);//15
                        current.reminingQ=current.reminingQ-((int) Math.ceil((double) current.quantum / 4));
                        t += (int) Math.ceil((double) current.quantum / 4);//2

                    }

                    //increase the times
                    current.times++;//2
                    if (current.burstTime2 == 0){
                        counter++;
                        current.times=0;
                        current.order=counter;//new edit
                        current.quantum=0;//set the quantum time by zero
                        ready_queue.remove(current);
                        current=ready_queue.peek();

                    }
                }


            }
            //if process arrive.
            for (int x=0;x<p.size();x++)
            {
                if ((t>=p.get(x).arrivalTime))//add condition
                {
                    ready_queue.add(p.get(x));
                    p.remove(i);
                }
            }
            //check for the piriorty
            if(ready_queue.isEmpty()){
                break;
            }

            if (current.times == 2)
            {
                boolean check = true;
                Process found = new Process();
                for (Process item : ready_queue)
                {
                    if (item.priority < current.priority && item.times!=0) {
                        check = false;
                        found = item;
                    }
                }

                if (check)
                {
                    if (current.burstTime2 == 0)
                    {
                        counter++;
                        current.times=0;
                        current.quantum=0;
                        current.order=counter;//new edit
                        ready_queue.remove(current);
                        current=ready_queue.peek();

                    }
                    else
                    {

                        if(current.burstTime2<=(int) Math.ceil((double) current.quantum / 4))
                        {

                            current.reminingQ=current.reminingQ-current.burstTime2;/**/
                            t += current.burstTime2;
                            current.fi_time=t;
                            current.burstTime2 =0;//15

                        }
                        else
                        {
                            current.burstTime2 -=( (int) Math.ceil((double) current.quantum / 2)) - (int) Math.ceil((double) current.quantum / 4);//15
                            current.reminingQ=current.reminingQ-( ( (int) Math.ceil((double) current.quantum / 2)) - (int) Math.ceil((double) current.quantum / 4) );
                            t += ( (int) Math.ceil((double) current.quantum / 2)) - (int) Math.ceil((double) current.quantum / 4);//2
                        }

                        //increase the times
                        current.times++;//3
                        if (current.burstTime2 == 0)
                        {
                            counter++;
                            current.times=0;
                            current.quantum=0;
                            current.order=counter;//new edit
                            ready_queue.remove(current);
                            current=ready_queue.peek();

                        }

                    }

                }
                else
                {

                    Process current2 = current;
                    current = found;//ready queue
                    //update the quantum
                    int QAD = (int) Math.ceil((double) current2.reminingQ / 2);

                    current2.quantum += QAD;
                    current2.reminingQ=current2.quantum;
//                        current.update+=(current.quantum+=2);//new update

                    current2.times=1;
                    ready_queue.remove(current2);
                    ready_queue.add(current2);

                }
            }


            for (int x=0;x<p.size();x++)
            {
                if ((t>=p.get(x).arrivalTime))//add condi
                {
                    ready_queue.add(p.get(x));
                    p.remove(i);
                }
            }

            if(ready_queue.isEmpty()){
                break;
            }

            //check for the burst time
            if (current.times == 3)
            {

                boolean check2 = true;
                Process found2 = new Process();
                for (Process item2 : ready_queue)
                {

                    if (item2.burstTime2 < current.burstTime2 &&  item2.times!=0) {
                        check2 = false;
                        found2 = item2;
                    }
                }

                if (check2)
                {


                    //check for the busrt time is it has finished
                    if (current.burstTime2 == 0)
                    {
                        counter++;
                        current.times=0;
                        ready_queue.remove(current);
                        current=ready_queue.peek();
                        //check the ready queue
                        current.order=counter;//new edit

                    }
                    else
                    {

                        if(current.burstTime2<=(int) Math.ceil((double) current.quantum / 4))
                        {
                            current.reminingQ=current.reminingQ-current.burstTime2;
                            t += current.burstTime2;
                            current.fi_time=t;
                            current.burstTime2 =0;

                        }
                        else
                        {
                            current.burstTime2 -= (int) Math.ceil((double) current.quantum / 4);//15
                            current.reminingQ=current.reminingQ-((int) Math.ceil((double) current.quantum / 4));
                            t += (int) Math.ceil((double) current.quantum / 4);//2

                        }

                        current.times++;//2
                        if (current.burstTime2 == 0){
                            counter++;
                            current.times=0;
                            ready_queue.remove(current);
                            current=ready_queue.peek();
                            current.order=counter;//new edit

                        }

                        //add to the end of the queue
                        ready_queue.remove(current);
                         ready_queue.add(current);
                        //update rhe quantum

                        current.quantum+=2;

                        current.update+=(current.quantum+=2);//new update
                        current.reminingQ=current.quantum;
                        //update the times
                        current.times=1;

                        current=ready_queue.peek();

                    }
                }
                else
                {

                    //add to the ready queue
                    Process current3=current;
                    current=found2;
                    //update the quantum
                    current3.quantum+=current3.reminingQ;
                    current3.reminingQ=current3.quantum;
                    current3.times=1;
                    ready_queue.remove(current3);
                    ready_queue.add(current3);
//


                }


            }
            for (int x=0;x<p.size();x++)
            {
                if ((t>=p.get(x).arrivalTime))//add condi
                {
                    ready_queue.add(p.get(x));
                    p.remove(i);
                }
            }

            if(ready_queue.isEmpty()){
                break;
            }


        }
        System.out.println("OUT");

        calc();

    }
}



