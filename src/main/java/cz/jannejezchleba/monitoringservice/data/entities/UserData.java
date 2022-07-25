package cz.jannejezchleba.monitoringservice.data.entities;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@Entity(name = "user")
@Getter
@NoArgsConstructor
@Setter
@Table(name = "users")
@ToString(of = {"username", "email", "accessToken"})
public class UserData {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ")
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String accessToken;

    public User toDomain() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .accessToken(this.accessToken)
                .build();
    }

    public static UserData from(User endpoint) {

        return new UserData(
                endpoint.getId(),
                endpoint.getUsername(),
                endpoint.getEmail(),
                endpoint.getAccessToken()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserData userData = (UserData) o;

        return Objects.equals(id, userData.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
