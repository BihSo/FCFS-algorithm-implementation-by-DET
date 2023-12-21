package  Debug.Entity.FCFS;
import org.jetbrains.annotations.NotNull;
import java.util.*;
class Process implements Comparable<Process>{
    static int currentNumberOfProcess;
    static int currentTime;
    static float avgWaitingTime;
    static float avgTurnaroundTime;
    String name;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnaroundTime;
    int completionTime;
    Process(int arrivalTime, int burstTime){
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.name = "P" + ++currentNumberOfProcess;
    }
    @Override
    public String toString() {
        return name +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", waitingTime=" + waitingTime +
                ", turnaroundTime=" + turnaroundTime +
                ", completionTime=" + completionTime;
    }
    @Override
    public int compareTo(@NotNull Process o) {
        return this.arrivalTime - o.arrivalTime;
    }
}
public class Fcfs {
    public static void run(Scanner in) {
        List<Process> processes = new ArrayList<>();
        input(processes , in);
        Collections.sort(processes);
        int size = processes.size();
        for (int i = 0; i < size; i++) {
            if (processes.get(i).arrivalTime > Process.currentTime){
                Process.currentTime = 0;
            }
            Process.currentTime += processes.get(i).burstTime;
            processes.get(i).completionTime = Process.currentTime;
            processes.get(i).turnaroundTime = Math.max(processes.get(i).completionTime - processes.get(i).arrivalTime ,processes.get(i).burstTime);
            processes.get(i).waitingTime = Math.max(processes.get(i).turnaroundTime - processes.get(i).burstTime , 0 );
            Process.avgWaitingTime += (processes.get(i).waitingTime / (float) size);
            Process.avgTurnaroundTime += (processes.get(i).turnaroundTime / (float) size);
        }
        output(processes);
    }
    public static void output(List<Process> processes){
        for(Process process : processes) System.out.println(process);
        System.out.println("Average waiting " + Process.avgWaitingTime);
        System.out.println("Average turnaround " + Process.avgTurnaroundTime);
    }
    public static void input(List<Process> processes , Scanner in){
        System.out.println("Enter the number of processes");
        int num = in.nextInt();
        for (int i = 0; i < num ; i++) {
            System.out.println("Enter arrival time for P" + (i + 1));
            int at = in.nextInt();
            System.out.println("Enter burst time for P" + (i + 1));
            int bt = in.nextInt();
            processes.add(new Process(at , bt));
        }
    }
}
