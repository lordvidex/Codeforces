package com.unicorn;

import java.util.*;
import java.io.*;

public class Main {
    static FastScanner sc;
    static PrintWriter pw;
    static int cas = 1;
    static StringBuilder ans;

    static Team[] teams;

    public static void printOutput() {
        pw.println("Case #" + cas++ + ": " + ans.toString());
    }

    public static void solve() {
        ans = new StringBuilder();
        int t = sc.ni();
        int l = sc.ni();
        teams = new Team[t];
        for (int i = 0; i < t; i++) {
            teams[i] = new Team(i + 1);
        }
        int[] data;
        for (int i = 0; i < l; i++) {
            String log = sc.nextLine();
             data = Arrays.stream(log.split(" ")).mapToInt(Integer::parseInt).toArray();
            if (data[4] == 1) {
                Team t1 = teams[data[1] - 1];
                // check if the user has solved the problem before
                Problem p = new Problem(data[2], data[3]);
                if (!t1.done.contains(p)) {
                    t1.timestamp += data[0];
                    t1.done.add(p);
                    t1.score += data[3] * 100;
                }
            }
        }
        Arrays.sort(teams);
        for (int i = 0; i < teams.length; i++) {
            ans.append(teams[i].id);
            if (i != teams.length - 1) {
                ans.append(" ");
            }
        }
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

    static class Problem {
        int problem;
        int input;

        Problem(int problem, int input) {
            this.problem = problem;
            this.input = input;
        }

        @Override
        public boolean equals(Object o) {
            if(o == this)return true;
            if (o == null || getClass() != o.getClass()) return false;
            Problem problem1 = (Problem) o;
            return problem == problem1.problem && input == problem1.input;
        }

        @Override
        public int hashCode() {
            return Objects.hash(problem,input,50,"Jackson");
        }
    }

    static class Team implements Comparable<Team> {
        int score;
        int timestamp;
        int id;
        Set<Problem> done;

        Team(int id) {
            this.id = id;
            this.score = 0;
            this.timestamp = 0;
            this.done = new HashSet<>();
        }

        @Override
        public int compareTo(Team o) {
            if (o.score != score) {
                // largest score first
                return o.score - score;
            } else if (timestamp != o.timestamp) {
                // smallest timestamp first
                return timestamp - o.timestamp;
            } else {
                // smallest id first
                return id - o.id;
            }
        }
    }
}

