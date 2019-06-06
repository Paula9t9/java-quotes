import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Quote {

    public String getQuote(){


        try {
            Scanner scanner = new Scanner(new File("src/main/resources/recentquotes.json"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return "";
        }

        return "Insanity: doing the same thing over and over again and expecting different results.";
    }


}
