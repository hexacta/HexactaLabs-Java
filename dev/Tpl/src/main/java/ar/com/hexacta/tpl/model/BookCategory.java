package ar.com.hexacta.tpl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "book_categories")
public class BookCategory implements Serializable {

    private static final long serialVersionUID = -8084614446913836469L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Long version;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    public BookCategory() {
        super();
    }

    public BookCategory(final String aName, final String aDescription) {
        this();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
