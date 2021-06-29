import java.io.*;
import java.util.*;

public class Main {
    private static int holder = 0;
    private static void printPreamble(BufferedWriter bw) throws IOException{
        bw.write("class HymnData {\n"+
                "final int id;\n"+
                "final String title;\n"+
                "final List<String> contents;\n"+
                "const HymnData(this.id, this.title, this.contents);"+
                "}\n"+"List<HymnData> data = [\n");
    }
    private static void printFinish(BufferedWriter bw) throws IOException {
        bw.write("];");
    }
    private static boolean isContent(String line){
        String[] arr = line.split("(\\.)|(\\s+)");
        try{
            holder = Integer.parseInt(arr[0]);
            return false;
        }catch(Exception e){
            return true;
        }
    }
    private static void writeHymnData(BufferedWriter bw,String title, ArrayList<String> stanzas) throws IOException{
        isContent(title);

        bw.write("HymnData("+holder+
                ", '"+
                title.replaceFirst(holder+"\\.*","").trim()+
                "', [" +
                String.join(",\n",stanzas) +
                "]),\n");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("holy_hymn.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("test.dart"));

        printPreamble(bw);

        // every item in this queue are the items in a single hymn
        // where the first String is the first stanza of the hymn
        // and the last String is the last stanza of the hymn
        ArrayList<String> stanzas =  new ArrayList<>();

        // stanzas contains single lines in a stanza
        ArrayList<String> stanza = new ArrayList<>();
        // for holding each line
        String line;
        // holds string that is either a new hymn or a new stanza
        String temp = null;
        String title = null;
        while((line = br.readLine())!=null){
            line = line.trim();
            line = line.replaceAll("\\s{2,}"," ");
            if(!line.isEmpty()){
                if(isContent(line)){
                    if(temp!=null){
                        if(!stanza.isEmpty())stanzas.add("'''\n"+String.join("\n",stanza)+"\n'''");
                        stanza.clear();
                        stanza.add(temp);

                        temp = null;
                    }
                    stanza.add(line);
                }else {
                    if(temp != null){
                        if(!stanza.isEmpty())stanzas.add("'''\n"+String.join("\n",stanza)+"\n'''");
                        stanza.clear();
                        if(!stanzas.isEmpty()) writeHymnData(bw,title,stanzas);
                        stanzas.clear();
                        title = temp;
                    }
                    temp = line;
                }
            }
        }
        if(!stanzas.isEmpty()) writeHymnData(bw, title, stanzas);
        printFinish(bw);
        br.close();
        bw.flush();
        bw.close();
    }
}