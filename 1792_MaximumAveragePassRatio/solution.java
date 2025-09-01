import java.util.PriorityQueue;
import java.util.Comparator;
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxHeap = new PriorityQueue<double[]>((a, b) -> {
            double gain_a = (a[0] + 1) / (a[1] + 1) - a[0] / a[1];
            double gain_b = (b[0] + 1) / (b[1] + 1) - b[0] / b[1];
            return Double.compare(gain_b, gain_a);
        });
        for (int[] c : classes) {
            maxHeap.offer(new double[]{ (double)c[0], (double)c[1] });
        }
        for (int i = 0; i < extraStudents; i++) {
            if (maxHeap.isEmpty()) {
                break;
            }
            double[] bestClass = maxHeap.poll();
            bestClass[0]++;
            bestClass[1]++;
            maxHeap.offer(bestClass);
        }
        double totalRatioSum = 0.0;
        for (double[] c : maxHeap) {
            totalRatioSum += c[0] / c[1];
        }

        return totalRatioSum / classes.length;
    }
}