package Debug.Entity.Team;
import java.util.ArrayList;
import java.util.Collections;
class Process implements Comparable<Process>{
    static int currentNumberOfProcess = 1;
    int process;
    int arrivalTime;
    int burstTime;
    Process(int arrivalTime, int burstTime){
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.process = currentNumberOfProcess++;
    }
    @Override
    public int compareTo(Process o) {
        return this.arrivalTime - o.arrivalTime;
    }
}
class FCFS {
    public static void main(String[] args) {
        ArrayList<Process> arrayList = new ArrayList<Process>();
        arrayList.add(new Process(0 , 4));
        arrayList.add(new Process(4 , 3));
        arrayList.add(new Process(2 , 10));
        arrayList.add(new Process(3 , 2));
        arrayList.add(new Process(1 , 5));
        Collections.sort(arrayList);
        int n = arrayList.size();
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] sumPreBurst = new int[n];
        float avgWaiting = 0 , avgTurnaround = 0;
        sumPreBurst[0] = arrayList.get(0).burstTime;
        for (int i = 1; i < n ; i++) {
            sumPreBurst[i] = arrayList.get(i).burstTime + sumPreBurst[i-1];
        }
        for (int i = 1; i < n; i++) {// why start from 1 ?
            waitingTime[i] = Math.max(0 , sumPreBurst[i-1] - arrayList.get(i).arrivalTime);
        }
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = arrayList.get(i).burstTime + waitingTime[i];
        }
        for (int i = 0; i < n ; i++) {
            avgTurnaround += turnaroundTime[i];
            avgWaiting += waitingTime[i];
        }
        System.out.println("Process\t\tBurst Time\t\tArrival Time\t\tWaiting Time\t\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("\tP"+ arrayList.get(i).process + "\t\t\t" + arrayList.get(i).burstTime + "\t\t\t\t" + arrayList.get(i).arrivalTime + "\t\t\t\t\t" +
                    waitingTime[i] + "\t\t\t\t\t" + turnaroundTime[i]);
        }
        System.out.println("\nAvg Waiting : " + (avgWaiting/n));
        System.out.println("Avg Turnaround : " + (avgTurnaround/n));
    }
}