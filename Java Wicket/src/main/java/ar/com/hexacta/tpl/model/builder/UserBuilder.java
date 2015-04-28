package ar.com.hexacta.tpl.model.builder;

import ar.com.hexacta.tpl.model.User;

/**
 * TODO: description
 */
public class UserBuilder {

    private String name = "defaultName";

    private String password = "";

    public User build() {
        return new User(name, password);
    }

    public UserBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public UserBuilder withPassword(final String aPassword) {
        password = aPassword;
        return this;
    }
}
