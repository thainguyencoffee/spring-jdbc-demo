# Spring JDBC Demo

[![Build Stage](https://github.com/thainguyencoffee/spring-jdbc-demo/actions/workflows/commit-stage.yaml/badge.svg)](https://github.com/thainguyencoffee/spring-jdbc-demo/actions/workflows/commit-stage.yaml)

### Domain model
```java
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

    // business logic
    public UserApplication(String city, String street, String username, String firstName, String lastName, String email) {
        this.id = UUID.randomUUID();
        this.address = new HomeAddress(city, street);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = new Email(email);
    }

}

```


### Init SQL
```sql
create table user_application (
    id uuid default gen_random_uuid() not null ,
    username varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    city varchar(255) not null,
    street varchar(255) not null,
    email varchar(255) not null
);
```

## Test

1. Run this command to test for insertion (insert / save) and selection (findAll) of data from the database.

```bash
./gradlew test --tests "springjdbcdemo.domain.UserApplicationJdbcTests.shouldThrows"
```

Result: insert successfully but select fails
Exception: org.springframework.data.mapping.MappingException
Cause: No property null found on entity class springjdbcdemo.domain.UserApplication to bind constructor parameter to!

### Please help me to fix this issue. Thanks!
### Either create a PR or I'll fix it. I will appreciate your help. Thanks!