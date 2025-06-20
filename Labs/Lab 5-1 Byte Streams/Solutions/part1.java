import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteCopy {

    public static void main(String[] args) throws IOException {

        FileInputStream infile = null;
        FileOutputStream outfile = null;
        byte b = 0;
        int byteCount = 0;

        try {
            infile = new FileInputStream("SampleText.txt");
            outfile = new FileOutputStream("Copy.txt");
            while ((b = (byte)infile.read()) != -1) {
                outfile.write(b);
                byteCount++;
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (infile != null) infile.close();
            if (outfile != null) outfile.close();
        }
        System.out.println(byteCount + " bytes copied");
    }
}