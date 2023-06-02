package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();
        int[] buckets = new int[M];

        for (Oomage o: oomages) {
            int hashCode = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[hashCode] += 1;
        }

        int low = N / 50;
        int hight = (int) (N / 2.5);

        for (int i : buckets) {
            if (i < low || i > hight) {
                return false;
            }
        }
        return true;
    }
}
