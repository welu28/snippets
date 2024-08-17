package snippets;
import java.util.*;

public class snippets {
    public static long modExp(long a, long b, long M){
        long res = 1;
        while(b > 0){
            if((b & 1) == 1){
                res = (res*a) % M;
            }
            b = b >> 1;
            a = (a*a)%M;
    
        }
        return res;
    }

    public static int[] readArr(Scanner io) {
        return Arrays.stream(io.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    public static int kadanes(int[] arr) {
        int max_so_far = arr[0];
        int max_ending_here = arr[0];

        for(int i = 1; i < arr.length; i++) {
            max_ending_here = Math.max(arr[i], max_ending_here + arr[i]);
            max_so_far = Math.max(max_ending_here, max_so_far);
        }

        return max_so_far;
    }

    public static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    // this is why I should switch to cpp
    static class Pair implements Comparable<Pair> {
        long turn;
        int index;
        
        Pair(long turn, int index) {
            this.turn = turn;
            this.index = index;
        }
        
        @Override
        public int compareTo(Pair other) {
            if (this.turn != other.turn) {
                return Long.compare(this.turn, other.turn);
            }
            return Integer.compare(this.index, other.index);
        }
    }
}
