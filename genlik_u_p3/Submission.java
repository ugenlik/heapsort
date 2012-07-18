
import java.io.*;
import java.nio.charset.Charset;

public class Submission {

    /**
     * The main
     * @param args Input file, Output file
     */
    public static void main(String[] args) {
        if (args.length==2) {
            try {
                //Open input/output files
                InputStream fis = new FileInputStream(args[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
                BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));

                //read the first line
                String line = br.readLine();

                //create a heap with this capacity
                Heap heap = new Heap(Integer.valueOf(line).intValue());

                //read each line
                while ((line = br.readLine()) != null) {

                    //Write it to the output
                    out.write(line);out.newLine();

                    //Tokenize output
                    String[] tk = line.split(" ");

                    //Check the instruction
                    if ("insert".equals(tk[0])) {
                        //Inesert to the ehap
                        heap.insert(new Heap.HeapItem(tk[1], Integer.valueOf(tk[2]).intValue()));
                        out.write("inserted "+tk[1]+" with priority "+tk[2]);out.newLine();
                    }
                    else if ("print".equals(tk[0])) {
                        //Print the heap
                        for (int i = 0; i < heap.size(); i++) {
                            out.write("node " + i + " = " + heap.at(i).name + " " + heap.at(i).key);out.newLine();
                        }
                    }
                    else if ("find".equals(tk[0])) {
                        //Find in the heap
                        out.write(tk[1] + (heap.find(tk[1])!=null ? " exists" : " is not") + " in the extended heap");out.newLine();
                    }
                    else if ("deleteMin".equals(tk[0])) {
                        //Delete the weakest
                        Heap.HeapItem hi;
                        if ((hi = heap.deleteMin())!=null)
                            out.write("deleted "+hi.name+" with priority " + hi.key);
                        else
                            out.write("extended heap has nothing");
                        out.newLine();
                    }
                    else if ("change".equals(tk[0])) {
                        //Change priority
                        heap.changeKey(tk[2], Integer.valueOf(tk[3]).intValue());
                        out.write("changed priority of "+tk[2]+" to " + tk[3]);out.newLine();
                    }
                }

                //File done
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
