# Java Quote

This application shows random quotes using Gson.

When connected to the internet, it will pull a random quote from the 
[Star Wars Quotes API](http://swquotesapi.digitaljedi.dk/api/SWQuote/RandomStarWarsQuote)

When not connected to the internet, it will pull a random quote from a file containing both quotes from around the 
world and your recent quotes from the Star Wars API. 

## How to Use


There is currently no front end for this application. 

To get a quote, run 

    ./gradlew run
        
 from your command line. 
 

You should see a random quote print out to your terminal. 