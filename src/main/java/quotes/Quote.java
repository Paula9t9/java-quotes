package quotes;

import com.google.gson.Gson;
import com.sun.javafx.binding.StringFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Quote {
    private String author;
    private String text;

    private Quote[] quotes;

    public String getQuote(){


        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("src/main/resources/recentquotes.json"));

            Gson gson = new Gson();

            quotes = gson.fromJson(bufferedReader, Quote[].class);

            int randomIndex = (int) (Math.random() * quotes.length);

            return quotes[randomIndex].toString();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return "Insanity: doing the same thing over and over again and expecting different results.";
        }

    }

    @Override
    public String toString() {
        return String.format("%s" +
                "\n%s", this.text, this.author);
    }
}
