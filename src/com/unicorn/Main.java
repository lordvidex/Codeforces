package com.unicorn;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    static FastScanner sc;
    static PrintWriter pw;
    static int cas = 1;
    static String ans;

    static HashMap<Character, char[]> keyPress = new HashMap<>() {{
        put('2', new char[]{'A', 'B', 'C'});
        put('3', new char[]{'D', 'E', 'F'});
        put('4', new char[]{'G', 'H', 'I'});
        put('5', new char[]{'J', 'K', 'L'});
        put('6', new char[]{'M', 'N', 'O'});
        put('7', new char[]{'P', 'Q', 'R', 'S'});
        put('8', new char[]{'T', 'U', 'V'});
        put('9', new char[]{'W', 'X', 'Y', 'Z'});
    }};

    static int[][] scoreBoard = new int[26][];
    static TreeSet<Item> answerSet;

    public static void printOutput() {
        pw.println("Case #" + cas++ + ": " + ans);
    }

    public static void bfs(String curr, LinkedList<Character> pressedNumbers, int score) {
        if (pressedNumbers.size() == 0) {
            answerSet.add(new Item(score, curr));
            return;
        }
        char start = pressedNumbers.pop();
        char[] startChars = keyPress.get(start);
        char lastChar = curr.charAt(curr.length() - 1);
        for (char startChar : startChars) {
            bfs(curr + startChar, new LinkedList<>(pressedNumbers), score + scoreBoard[lastChar - 'A'][startChar - 'A']);
        }
    }

    public static void solve() {
        answerSet = new TreeSet<>();
        int l = sc.ni();
        int k = sc.ni();
        for (int i = 0; i < 26; i++) {
            scoreBoard[i] = new int[26];
            for (int j = 0; j < 26; j++) {
                scoreBoard[i][j] = sc.ni();
            }
        }
        String userInput = sc.next();
        LinkedList<Character> linkedList = userInput.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedList::new));
        char start = linkedList.pop();
        char[] startChars = keyPress.get(start);
        for (char startChar : startChars) {
            bfs(startChar + "", new LinkedList<>(linkedList), 0);
        }
        ans = answerSet.toArray(new Item[0])[k - 1].string;
        printOutput();
    }

    public static void main(String[] args) throws FileNotFoundException {
        sc = new FastScanner("input-3.txt");
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

    static class Item implements Comparable<Item> {
        int score;
        String string;

        public Item(int score, String string) {
            this.score = score;
            this.string = string;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "score=" + score +
                    ", string='" + string + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Item o) {
            if (score != o.score) {
                return o.score - score;
            } else {
                return string.compareTo(o.string);
            }
        }
    }
}

