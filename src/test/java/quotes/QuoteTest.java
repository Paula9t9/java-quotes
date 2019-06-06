package quotes;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuoteTest {

    @Test
    public void testGetQuote_notNull(){
        Quote quote = new Quote();

        assertNotNull("Should not return null", quote.getQuote());
    }


}