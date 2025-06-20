import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LineCopy {


    public static void main(String[] args) throws IOException {

        FileReader infile = null;
        FileWriter outfile = null;
        BufferedReader inbuff = null;
        BufferedWriter outbuff = null;

        String line = null;

        try {
            infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
            inbuff = new BufferedReader(infile);
            outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);
            outbuff = new BufferedWriter(outfile);


            while ((line = inbuff.readLine()) != null) {
                outbuff.write(line);
                outbuff.newLine();
            }

        } catch (IOException e) {
            System.out.println(e);

        } finally {
            outbuff.flush();
            if (inbuff != null) inbuff.close();
            if (outbuff != null) outbuff.close();

        }
    }

}
