package ar.com.hexacta.tpl.model;

import org.junit.Assert;
import org.junit.Test;

public class BookGenreTest {

    @Test
    public void testGenreCreation() {
        Assert.assertEquals(BookGenre.HUMOR.toString(), "HUMOR");
    }

}
