package ar.com.hexacta.tpl.model;

import java.io.Serializable;

// @Entity
// @Table (name = "USERS")
public class User extends Entidad implements Serializable {
    private static final long serialVersionUID = -2599013520313365015L;

    /*
     * @Id
     *
     * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
     *
     * @Version private Long version;
     */

    // @Column(name = "USERNAME")
    private String username;

    // @Column(name = "EMAIL")
    private String email;

    // @Column(name = "PASSWOD")
    private String password;

    // @Column(name = "ENABLED")
    private boolean enabled;

    // Hibernate needs
    public User() {
        super();
    }

    public User(final String username, final String pass) {
        super();
        this.username = username;
        password = pass;
        enabled = true;
    }

    public User(final String username, final String pass, final String email) {
        super();
        this.username = username;
        password = pass;
        this.email = email;
        enabled = true;
    }

    public User(final String username, final String pass, final String email, final boolean enabled) {
        super();
        this.username = username;
        password = pass;
        this.email = email;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(final String user) {
        username = user;
    }

    public void setPassword(final String pass) {
        password = pass;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

}
