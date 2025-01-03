package snippets;
import java.util.*;

public class snippets {
	public static final long MOD = 1000000007L;
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

	// kadanes max subarray sum
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

    public static boolean nextPermutation(int[] array) { // go through all permutations lexicographically
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) i--;
        if (i < 0) return false;
        int j = array.length - 1;
        while (array[j] <= array[i]) j--;
        swap(array, i, j);
        for (int k = i + 1, l = array.length - 1; k < l; k++, l--) {
            swap(array, k, l);
        }
        return true;
    }
    

    public static int[][] rotate(int[][] arr, int n) { // rotate 45 degrees 
        int N = 2 * n - 1; // new size
		int[][] field = new int[N][N];
		for (int r = 0; r < N; r++) {
			Arrays.fill(field[r], -1);  // -1's indicate invalid locations (for prefix sums)
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				field[i + j][n - i + j - 1] = arr[i][j];
			}
		}
        return field;
    }

	// prefix for kmp
    public static int[] kmpPrefix(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = f[j];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            f[i + 1] = j;
        }
        
        return f;
    }

	// returns the 4 neighbors (shares 1 edge) of a cell in a matrix
public static int[][] neighbors(int col, int row, int n, int m) {
        int[][] res = new int[4][2];
        for (int[] neighbor : res) Arrays.fill(neighbor, -1);
        int[][] directions = {
            {0, -1},  // left
            {0, 1},   // right
            {-1, 0},  // up
            {1, 0}    // down
        };

        for (int i = 0; i < directions.length; i++) {
            int newCol = col + directions[i][0];
            int newRow = row + directions[i][1];
            if (newCol >= 0 && newCol < n && newRow >= 0 && newRow < m) {
                res[i][0] = newCol;
                res[i][1] = newRow;
            }
        }

        return res;
    }

	// returns number of ways to achieve a sum using the elemnts w/ O(n*target) dp
public static long dp(int[] nums, int target) {
        int n = nums.length;
        long[][] dp = new long[n + 1][target + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - nums[i - 1]]) % MOD;
                }
            }
        }

        return dp[n][target] % MOD;
    }

	// for interactive problems
public static int makeQuery(int a, int b, FastIO io) {
        System.out.print("? " + a + " " + b);
        System.out.flush();
        int n = io.nextInt();
        return n;
    }

static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

public static void sortReverse(int[] array) {
        Arrays.sort(array);
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

private static long pow(int base, int exp, int mod) {
        long result = 1;
        long current = base % mod;
        
        while (exp > 0) {
            if ((exp & 1) == 1) {  // if exp is odd
                result = (result * current) % mod;
            }
            current = (current * current) % mod;  // square the base
            exp >>= 1;  // divide exp by 2
        }
        
        return result;
    }

private static int binarySearchLeft(long[] arr, long target, int start) {
        int low = start, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int binarySearchRight(long[] arr, long target, int start) {
        int low = start, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
