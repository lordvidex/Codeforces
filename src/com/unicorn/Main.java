package com.unicorn;

import java.util.*;
import java.io.*;

public class Main {
    static FastScanner sc;
    static PrintWriter pw;
    static int cas = 1;
    static int ans;


    public static void printOutput() {
        pw.println("Case #" + cas++ + ": " + ans);
    }

    public static void solve() {

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

