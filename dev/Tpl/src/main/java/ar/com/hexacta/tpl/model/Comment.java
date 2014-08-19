/**
 *
 */
package ar.com.hexacta.tpl.model;

import java.io.Serializable;

// @Entity
// @Table(name = "COMMENTS")
public class Comment extends Entidad implements Serializable {

    private static final long serialVersionUID = 537917183637872456L;

    // @Id
    // @Column(name = "COMMENT_ID")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    /*
     * @Version
     *
     * @Column(name = "VERSION") private Long version;
     */
    // @Column(name = "BOOK")
    private Long bookId;

    // @Column(name = "USER")
    private String user;

    // @Column(name = "BODY")
    private String body;

    // Hibernate needs
    public Comment() {
        super();
    }

    public Comment(final Long bookId, final String user, final String body) {
        super();
        this.bookId = bookId;
        this.user = user;
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(final Long bookId) {
        this.bookId = bookId;
    }
    
}
