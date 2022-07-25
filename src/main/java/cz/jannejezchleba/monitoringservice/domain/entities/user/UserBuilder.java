package cz.jannejezchleba.monitoringservice.domain.entities.user;

public final class UserBuilder {
    private int id;
    private String username;
    private String email;
    private String accessToken;

    UserBuilder() { }

    public UserBuilder id(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public User build() {
        return new User(this.id, this.username, this.email, this.accessToken);
    }
}
