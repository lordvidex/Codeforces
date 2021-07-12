import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int holder = 0;
    private static void printPreamble(BufferedWriter bw) throws IOException{
        bw.write("List<HymnData> responsive = [\n");
    }
    private static void printFinish(BufferedWriter bw) throws IOException {
        bw.write("];");
    }
    private static boolean isContent(String line){
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(line);
        if(!m.find()){
            return true;
        }else {
            holder = Integer.parseInt(m.group());
            return !line.startsWith(m.group());
        }
    }
    private static void writeHymnData(BufferedWriter bw,String title, ArrayList<String> stanzas) throws IOException{
        isContent(title);

        bw.write("HymnData("+holder+
                ", '"+
                title.replaceFirst(holder+"\\.*","").trim()+
                "', [\n" +
                String.join(",",stanzas) +
                "\n]),\n");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("responsive.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("test.dart"));

        printPreamble(bw);

        // stanzas contains single lines in a stanza
        ArrayList<String> stanza = new ArrayList<>();
        // for holding each line
        String line;
        // holds string that is either a new hymn or a new stanza
        String title = null;

        while((line = br.readLine())!=null){
            line = line.trim();
            line = line.replaceAll("\\s{2,}"," ");
            line = line.replaceAll("[^\\p{Graph}\n\r\t ]", "");
            line = line.replaceAll("'","\\\\'");
            if(!line.isEmpty()){
                if(isContent(line)){
                    stanza.add("'"+line+"'");
                }else {
                    if(!stanza.isEmpty()){
                        writeHymnData(bw,title,stanza);
                        stanza.clear();
                    }
                    title = line;
                }
            }
        }
        if(!stanza.isEmpty()){
            writeHymnData(bw,title,stanza);
            stanza.clear();
        }
        printFinish(bw);
        br.close();
        bw.flush();
        bw.close();
    }
}