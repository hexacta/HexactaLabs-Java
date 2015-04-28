package ar.com.hexacta.tpl.model;

public class BookCategory extends Entity {

    private static final long serialVersionUID = -8084614446913836469L;

    private String name;

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

}
