/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;
import java.util.Scanner;
//CBL
/**
 *
 * @author rawan
 */
public class SJF {

    static int Number_of_process;
    static Scanner input = new Scanner(System.in);
    
    /** This method to get burst and arrival time from user. */
    public static void getProcesses (int burst[], int process[], int arrival[], int flag[]){
        
        System.out.print("\nEnter burst time for each process!\n");
    
        for( int i=0; i < Number_of_process; i++ ) {

            System.out.print("\nProcess["+(i+1)+"]: ");
            burst[i] = input.nextInt();
            process[i] = i+1;
            flag[i] = 0; // process is not completed
        }
         System.out.print("\nEnter arrival time for each process!\n");
        
        for( int i=0; i < Number_of_process; i++ ) {

            System.out.print("\nProcess["+(i+1)+"]: ");
            arrival[i] = input.nextInt();
        }
    } //End processes method
    
    /** Shortest Job First Algorithm. */
    public static void SJF (int burst[], int turnAround[],int complete[], 
                    int waiting[], int arrival[], int sysTimer, int flag[], int TotalNProcess ) {
         
        while(true) {

            int temp2 = Number_of_process, minute = 999;

            /* This condition to terminate the loop */
            if (TotalNProcess == Number_of_process)  
                break;

            for (int i=0; i < Number_of_process; i++) {

            /* This condition will be executed for the first process */
            if ((arrival[i] <= sysTimer) && (flag[i] == 0) && (burst[i] < minute)) { 

                minute = burst[i];
                temp2 = i; //Store the first process id
            } 
                 } // End for loop
            /* This condition to increase the system time, because no process arrival time */
            if (temp2 == Number_of_process)
                sysTimer++;

            else { /**  calculate complete, turnAround and waiting time. */

                complete[temp2] = sysTimer + burst[temp2];//store the time the proces finished 2+4
                sysTimer += burst[temp2]; // 6s
                turnAround[temp2] = complete[temp2] - arrival[temp2];
                waiting[temp2] = turnAround[temp2] - burst[temp2];
                flag[temp2]=1;// Process flag is finish
                TotalNProcess++;
              }
         } // End while 
    
    } //End SJF method
     
     
    /** This method to display Info. */
    public static void Display (int process[],int burst[], int turnAround[], int complete[], int waiting[], 
                                            int arrival[], double wait_avg ) {
        
          System.out.println("\nProcess\t Burst Time\tArrival Time\tComplete Time\tTurnaround Time\t Waiting Time");
          
            for(int j = 0; j < Number_of_process; j++) {
                wait_avg += waiting[j];
                System.out.println(process[j]+"\t "+burst[j]+"\t\t"+arrival[j]+"\t\t"+complete[j]+
                                                "\t\t"+turnAround[j]+"\t\t "+waiting[j]); 
            }
            System.out.println("\nAverage waiting Time is: " + String.format("%.2f",(double)(wait_avg / Number_of_process)));
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.print("\nEnter number of process: ");
        Number_of_process = input.nextInt(); // Number of process

       // Ctreate six arrays to store each one of these:
        int processID[] = new int[Number_of_process];
        int burstTime[] = new int[Number_of_process];
        int arrivalTime[] = new int[Number_of_process];  
        int completeTime[] = new int[Number_of_process];
        int turnAroundTime[] = new int[Number_of_process]; 
        int waitingTime[] = new int[Number_of_process];
        int flag[] = new int[Number_of_process];  // To checks process is completed or not

        int TotalNProcess = 0, sysTimer = 0;
        double wait_avg = 0;

        getProcesses(burstTime, processID, arrivalTime, flag);
        SJF(burstTime, turnAroundTime, completeTime, waitingTime, arrivalTime, sysTimer,  flag, TotalNProcess );
        Display(processID, burstTime, turnAroundTime,completeTime, waitingTime, arrivalTime, wait_avg );
    
        } // End main method
} // End class SJF 

        
    
    
