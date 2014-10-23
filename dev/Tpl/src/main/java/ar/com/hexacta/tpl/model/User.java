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
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -2599013520313365015L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWOD")
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled;

    public User() {
        super();
    }

    public User(final String username, final String pass) {
        this();
        this.username = username;
        password = pass;
        enabled = true;
    }

    public User(final String username, final String pass, final String email) {
        this(username, pass);
        this.email = email;
    }

    public User(final String username, final String pass, final String email, final boolean enabled) {
        this(username, pass, email);
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }
}
