package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    /** Count the time of AListConstruction.
     *  @author CuiYuxin
     */
    public static void timeAListConstruction() {
        int[] Ns = {1000,2000,4000,8000,16000,32000,64000,128000};
        AList<Integer> NsList = new AList<Integer>();
        AList<Double> timesList = new AList<Double>();
        AList<Integer> opCountsList = new AList<Integer>();
        for(int N : Ns) {
            Stopwatch sw = new Stopwatch();
            AList<Integer> list = new AList<Integer>();
            for(int i = 0; i < N; i += 1) {
                list.addLast(i);
            }
            double timeInSeconds = sw.elapsedTime();
            NsList.addLast(N);
            timesList.addLast(timeInSeconds);
            opCountsList.addLast(N);
        }
        printTimingTable(NsList, timesList, opCountsList);
    }
}
