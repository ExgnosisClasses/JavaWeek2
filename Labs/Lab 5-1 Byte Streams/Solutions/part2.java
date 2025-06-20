
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteCopy {


    public static void main(String[] args) throws IOException {

        FileInputStream infile = null;
        FileOutputStream outfile = null;

        byte[] b = new byte[128];

        int inputCount = 0;
        int byteCount = 0;
        int bytesRead = 0;

        try {
            infile = new FileInputStream("SampleText.txt");
            outfile = new FileOutputStream("Copy.txt");

            while ((bytesRead = infile.read(b)) != -1){
                outfile.write(b);

                byteCount = byteCount + bytesRead;
                inputCount++;
                System.out.println("inputCount=" + inputCount + " bytesRead =" + bytesRead );
            }

        } catch (IOException e) {
            System.out.println(e);

        } finally {
            infile.close();
            outfile.close();

        }
        System.out.println(byteCount + " bytes copied");
    }

}