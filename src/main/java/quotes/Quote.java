package quotes;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sun.javafx.binding.StringFormatter;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quote {
    private String author;
    private String text;
    private String starWarsQuote;

    private List<Quote> quotes;

    // Gets a random quote from the swquotes API. If that fails, it will pull a random quote from out file
    public String getQuote(){

        try{
            return fetchStarWarsQuote();

        }catch (IOException e){
            System.out.println("Error connecting to API");
            System.out.println(e);

            // If we can't get a quote from the API, pull one from the file
            try {
                return fetchRandomQuoteFromFile();
            } catch (FileNotFoundException e2) {
                System.out.println("File not found");
                e2.printStackTrace();
                //If we can't pull a quote from the file, return this winner
                return "Insanity: doing the same thing over and over again and expecting different results.";
            }
        }
    }

    public String fetchStarWarsQuote() throws IOException {
        // Get starwars quote from starwars API
        URL url = new URL("http://swquotesapi.digitaljedi.dk/api/SWQuote/RandomStarWarsQuote");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader internetReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        Gson gson = new Gson();
        Quote newQuote = gson.fromJson(internetReader, Quote.class);
        // store sw quote in text for easier access later
        newQuote.text = newQuote.starWarsQuote;
        saveQuote("src/main/resources/recentquotes.json", newQuote);

        return newQuote.text;
    }


    public String fetchRandomQuoteFromFile() throws FileNotFoundException {
        deserializeJsonFile();
        int randomIndex = (int) (Math.random() * quotes.size());
        return quotes.get(randomIndex).toString();
    }

    // save to json file
    public void saveQuote(String filepath, Quote newQuote){

        try {
            // TODO: Should check if quote is already in the file
            Gson gson = new Gson();
            gson.toJson(newQuote, new FileWriter(filepath));
        } catch (IOException e) {
            System.out.println("Error saving new quote to file");
            e.printStackTrace();
        }
    }

    // Stores quotes from file into quotes list
    // Used List trick from https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
    public void deserializeJsonFile() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/recentquotes.json"));
        Gson gson = new Gson();
        Type quoteListType = new TypeToken<ArrayList<Quote>>(){}.getType();
        quotes = gson.fromJson(bufferedReader, quoteListType);
    }


    @Override
    public String toString() {
        return String.format("%s" +
                "\n%s", this.text, this.author);
    }
}
