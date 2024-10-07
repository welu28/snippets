import java.io.*;
import java.util.*;

// basic coord compression and dp (prefix sum + diff array)

public class D_Static_Range_Queries {
    public static long[] widths;
    public static long[] diff;
    public static long[] pref;
    public static long[] val;
    public static ArrayList<Integer> ind;
    public static Query[] updates;
    public static Query[] queries;
    public static int N;
    public static int Q;

    public static void main(String[] args) throws IOException {
        FastIO io = new FastIO();
        N = io.nextInt();
        Q = io.nextInt();
        init();
        readUpdates(io);
        readQueries(io);
        coordCompress();
        init2();

        // Calculate diff array
        for (int i = 0; i < N; i++) {
            Query q = updates[i];
            diff[getInd(q.l) + 1] += q.v;
            diff[getInd(q.r) + 1] -= q.v;
        }

        // Calculate widths
        int n = ind.size();
        for (int i = 0; i < n - 1; i++) {
            widths[i + 1] = ind.get(i + 1) - ind.get(i);
        }

        // Calculate array
        for (int i = 1; i < n; i++) {
            val[i] = val[i - 1] + diff[i];
        }

        // Calculate pref array
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + val[i] * widths[i];
        }

        for (int i = 0; i < Q; i++) {
            Query q = queries[i];
            long ans = pref[getInd(q.r)] - pref[getInd(q.l)];
            System.out.println(ans);
        }

        io.close();
    }

    public static int getInd(int a) {
        return Collections.binarySearch(ind, a); 
    }

    public static void init2() {
        int n = ind.size();
        diff = new long[n + 5];
        pref = new long[n + 5];
        widths = new long[n + 5];
        val = new long[n + 5];
    }

    public static void coordCompress() {
        TreeSet<Integer> sorted = new TreeSet<>(ind); // helpful b/c sorted
        ind.clear();
        ind.addAll(sorted);
    }

    public static void readQueries(FastIO io) {
        for (int i = 0; i < Q; i++) {
            int l = io.nextInt();
            int r = io.nextInt();
            ind.add(l);
            ind.add(r);
            queries[i] = new Query(l, r);
        }
    }

    public static void readUpdates(FastIO io) {
        for (int i = 0; i < N; i++) {
            int l = io.nextInt();
            int r = io.nextInt();
            int v = io.nextInt();
            ind.add(l);
            ind.add(r);
            updates[i] = new Query(l, r, v);
        }
    }

    public static void init() {
        ind = new ArrayList<>();
        updates = new Query[N];
        queries = new Query[Q];
    }

    public static class Query {
        public int l;
        public int r;
        public int v;

        public Query(int l, int r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }

        public Query(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static class FastIO {
        BufferedReader br;
        PrintWriter pw;
        StringTokenizer st;

        public FastIO() {
            br = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(new BufferedOutputStream(System.out));
        }

        public FastIO(String inputFile, String outputFile) throws IOException {
            br = new BufferedReader(new FileReader(inputFile));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        void print(Object obj) {
            pw.print(obj);
        }

        void println(Object obj) {
            pw.println(obj);
        }

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }
}
