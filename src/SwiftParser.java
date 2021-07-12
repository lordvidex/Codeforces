import java.io.*;

/**
 * Created by IntelliJ IDEA
 * Date: 02.07.2021
 * Time: 9:37 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public class SwiftParser {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("responsive.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("x.txt"));
        String line;
        while((line = br.readLine())!= null){
            String[] x = line.split(",");
            bw.write("Contact(name: \""+x[0]+"\", surname: \""+x[1]+"\", occupation: \""+x[2]+"\", telephone: \""+x[3]+"\"),");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
