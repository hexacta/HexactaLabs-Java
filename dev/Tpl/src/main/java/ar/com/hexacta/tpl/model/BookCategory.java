package ar.com.hexacta.tpl.model;

import java.io.Serializable;

// @Entity
// @Table(name = "BOOK_CATEGORIES")
public class BookCategory extends Entidad implements Serializable {

    private static final long serialVersionUID = -8084614446913836469L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    // @Version
    // private Long version;

    // @Column(name = "NAME")
    private String name;

    // @Column(name = "DESCRIPTION")
    private String description;

    public BookCategory() {
        super();
    }

    public BookCategory(final String aName, final String aDescription) {
        super();
        name = aName;
        description = aDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    /*
     * public Long getId() { return id; }
     *
     * public void setId(final Long id) { this.id = id; }
     *
     * public Long getVersion() { return version; }
     *
     * public void setVersion(final Long version) { this.version = version; }
     */
}
