package ar.com.hexacta.tpl.test;

import org.junit.Assert;
import org.junit.Test;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.Comment;

public class CommentTest {

    @Test
    public void testCommentCreation() {
        Comment comment = new Comment();

        Assert.assertNotNull(comment);
    }

    @Test
    public void testParamCommentCreation() {

        String testUser = "test@mail.com";
        String testBody = "Test comment body";
        Long testBook = new Long(1);

        Comment comment = new Comment(testBook, testUser, testBody);

        Assert.assertTrue(comment.getUser() == testUser);
        Assert.assertTrue(comment.getBody() == testBody);
        Assert.assertTrue(comment.getBookId() == testBook);
    }

    @Test
    public void testCommentDAO() {
    }
}
