package com.unicorn;

import java.util.*;
import java.io.*;

public class Main {
    static FastScanner sc;
    static PrintWriter pw;
    static int cas = 1;
    static long ans;


    public static void printOutput() {
        pw.println("Case #" + cas++ + ": " + ans);
    }
    static long SumGPUtil(long r, long n,
                          long m)
    {

        // Base cases
        if (n == 0)
            return 1;
        if (n == 1)
            return (1 + r) % m;

        long ans;

        // If n is odd
        if (n % 2 == 1)
        {
            ans = (1 + r) *
                    SumGPUtil((r * r) % m,
                            (n - 1) / 2, m);
        }
        else
        {
            // If n is even
            ans = 1 + (r * (1 + r) *
                    SumGPUtil((r * r) % m,
                            (n / 2) - 1, m));
        }

        return (ans % m);
    }

    public static void solve() {
        long r = sc.nl();
        long n = sc.nl();
        long m = sc.nl();
        ArrayList<Long> list = new ArrayList<>();
        n = n*n;
//        ans = 1;
//        long cell = 1;
//        for (int i = 1; i < n; i++) {
//            cell = (cell * r) % m;
//            ans = (ans + cell) % m;
//            System.out.println(i + " " + ans);
//            if (list.isEmpty() || list.get(0) != ans) {
//                list.add(ans);
//            } else break;
//        }
//        int rem = (int) ((n - 1) % list.size());
//        int pos = (rem - 1 + list.size()) % list.size();
        ans = SumGPUtil(r,n,m)%m;

//        BigInteger pseudoAns = (pow(BigInteger.valueOf(r),BigInteger.valueOf(n)).subtract(BigInteger.ONE)).divide(BigInteger.valueOf(r).subtract(BigInteger.ONE)).mod(BigInteger.valueOf(m));
//        ans = pseudoAns.longValue();
        printOutput();
    }

    public static void main(String[] args) throws FileNotFoundException {
        sc = new FastScanner("input.txt");
        pw = new PrintWriter("output.txt");
        int t = sc.ni();
        while (t-- > 0) {
            solve();
        }

        sc.close();
        pw.close();
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(String filePath) {
            try {
                br = new BufferedReader(new FileReader(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        int ni() {
            return Integer.parseInt(next());
        }

        long nl() {
            return Long.parseLong(next());
        }

        double nd() {
            return Double.parseDouble(next());
        }

        /**
         * @return an array of characters from the string read using next();
         */
        char[] nc() {
            return next().toCharArray();
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}

