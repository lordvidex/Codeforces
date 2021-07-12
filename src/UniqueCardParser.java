import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA
 * Date: 06.07.2021
 * Time: 4:24 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc: There are 2606 unique cards and 3312 total cards
 */
public class UniqueCardParser {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("x.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("xa.txt"));
        String line;
        while((line = br.readLine())!= null){
            bw.write(line.replaceAll("[{}\"\\s]+",""));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

        // now read from xa.txt to swift.swift
        br = new BufferedReader(new FileReader("xa.txt"));
//        bw = new BufferedWriter(new FileWriter("swift.swift"));
        HashSet<Card> set = new HashSet<>();
        while((line = br.readLine())!= null){
            set.add(new Card(line.split(",")));
        }
        br.close();
        System.out.println(set.size());
    }
    static class Card {
        String[] arr;
        public Card(String[] arr){
            this.arr = arr;
        }
        @Override
        public int hashCode() {
            int hashCode = 0;
            for (int i = 0; i < arr.length; i++) {
                hashCode += arr[i].hashCode() * 31 ^ i;
            }
            return hashCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Card card = (Card) o;
            return Arrays.equals(arr, card.arr);
        }
    }
}
