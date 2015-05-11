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
    private static final long serialVersionUID = 1588265571172283477L;
    
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

    public User() {
        super();
    }

    public User(final String aUserName, final String aPassword) {
        this();
        username = aUserName;
        email = aPassword;
    }
    
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setUsername(final String name) {
        username = name;
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
