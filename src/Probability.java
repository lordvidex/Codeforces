import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA
 * Date: 22.10.2021
 * Time: 9:33 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public class Probability {
    public static ArrayList<String> permutations = new ArrayList<>();

    public static void printAll(char[] c, int n, String start){
        if(start.length() >= n){
            System.out.println(start);
            permutations.add(start);
        }else{
            for(char x: c){
                printAll(c, n, start+x);
            }
        }
    }

    public static void main(String[] args) {
        char[] x = new char[]{'0','1','2','3'};
        HashMap<Character,Double> p = new HashMap<>(){{
            put('0', 0.1);
            put('1', 0.2);
            put('2', 0.3);
            put('3', 0.4);
        }};

        int n = 5; // кол. попытка

        // get all permutations of x
        printAll(x,n,"");

        // решение
        HashMap<Integer,Double> results = new HashMap<>();
        for(String each: permutations) {
            int digitSum = 0;
            // digitSum = 0
            double result = 1;
            for (char _char: each.toCharArray()) {
                digitSum += _char-'0';
                result*=p.get(_char);
            }
            results.put(digitSum, results.getOrDefault(digitSum,0.0)+result);
        }

        for (var entry: results.entrySet()) {
            System.out.println(entry.getKey()+":    "+entry.getValue());
        }
        double sum = 0.0;
        for(var entry: results.entrySet()) {
            sum+=entry.getValue();
        }
        System.out.println(sum);
    }
}
