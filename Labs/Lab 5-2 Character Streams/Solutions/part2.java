
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class CharCopy {


    public static void main(String[] args) throws IOException {

        FileReader infile = null;
        FileWriter outfile = null;

        char [] c = new char[128];

        int charCount = 0;
        int charsRead = 0;
        int readCount = 0;

        try {
            infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
            outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);

            while ((charsRead = infile.read(c)) != -1) {
                outfile.write(c);

                charCount = charCount + charsRead;
                readCount++;
                System.out.println("Read " + readCount + " Chars read " + charsRead);

            }

        } catch (IOException e) {
            System.out.println(e);

        } finally {
            if (infile != null) infile.close();
            if (outfile != null) outfile.close();

        }
        System.out.println(charCount + " characters copied");
    }

}
