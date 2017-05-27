
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Encrypt {

    private static final int[] chain = {
        1941, 12394, 23093, 9023, 2111, 193, 93, 293
    };

    //encrypt method
    public static String encrypt(String key) {
        String result = "";
        int l = key.length();
        char ch;
        int ck = 0;
        for (int i = 0; i < l; i++) {
            if (ck >= chain.length - 1) {
                ck = 0;
            }
            ch = key.charAt(i);
            ch += chain[ck];
            result += ch;
            ck++;
        }
        return result;
    }

    //decrypt method
    public static String decrypt(String key) {
        String result = "";
        int l = key.length();
        char ch;
        int ck = 0;
        for (int i = 0; i < l; i++) {
            if (ck >= chain.length - 1) {
                ck = 0;
            }
            ch = key.charAt(i);
            ch -= chain[ck];
            result += ch;
            ck++;
        }
        return result;
    }
    List<String> lines = new ArrayList<String>();
    String line = null;

    //quik encryption
    public void encr() {
        try {
            File f1 = new File("src\\Credentials\\credentials.txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);

            String content = content();

            String array[] = content.split(",");

            String original = array[0];

            String encrypted = Encrypt.encrypt(original);

            while ((line = br.readLine()) != null) {
                if (line.contains(original)) {
                    line = line.replace(original, encrypted);
                }
                lines.add(line);
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for (String s : lines) {
                out.write(s);
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //quik decryption 
    public void decr() {
        try {
            File f1 = new File("src\\Credentials\\credentials.txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);

            String content = content();

            String array[] = content.split(",");

            String original = array[0];

            String decrypted = Encrypt.decrypt(original);

            while ((line = br.readLine()) != null) {
                if (line.contains(original)) {
                    line = line.replace(original, decrypted);
                }
                lines.add(line);
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for (String s : lines) {
                out.write(s);
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //get the content of the text file as a string
    public String content() {
        BufferedReader br = null;
        String sCurrentLine = null;
        try {

            br = new BufferedReader(new FileReader("src\\Credentials\\credentials.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                return sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sCurrentLine;
    }

}
