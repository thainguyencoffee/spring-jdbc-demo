package springjdbcdemo.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("user_application")
@Getter
@ToString
public class UserApplication {
    @Id
    private UUID id;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private HomeAddress address;
    private String username;
    private String firstName;
    private String lastName;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Email email;

    public UserApplication(String city, String street, String username, String firstName, String lastName, String email) {
        this.id = UUID.randomUUID();
        this.address = new HomeAddress(city, street);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = new Email(email);
    }

    // add default constructor for Spring Data JDBC
    UserApplication() {
    }
}
