public class CharCopy {


    public static void main(String[] args) throws IOException {

        FileReader infile = null;
        FileWriter outfile = null;
        int c = 0;
        int charCount = 0;

        try {
            infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
            outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);

            while ((c = infile.read()) != -1) {
                outfile.write(c);
                charCount++;

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
