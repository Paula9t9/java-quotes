package quotes;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuoteTest {

    // To test if get a quote from file, just turn off wifi and rerun tests

    @Test
    public void testGetQuote_notNull(){
        Quote quote = new Quote();

        assertNotNull("Should not return null", quote.getQuote());
    }



}