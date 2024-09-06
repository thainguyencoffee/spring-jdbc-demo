package springjdbcdemo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.mapping.MappingException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class UserApplicationJdbcTests {
    @Container
    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16"));

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private UserApplicationRepository repository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;



    @Test
    void shouldThrows() {
        var u1 = new UserApplication("hanoi", "none",
                "thainguyencoffee", "Nguyen", "Thai", "test@gmail.com");
        var u2 = new UserApplication("danang", "none",
                "michaeljackson", "Michael", "Jackson", "test@gmail.com");
        var u3 = new UserApplication("phutho", "none",
                "joshlong", "Long", "Josh", "test@gmail.com");
        var u4 = new UserApplication("hanoi", "phamhung",
                "oliverdrotbohm", "Oliver", "Drotbohm", "test@gmail.com");
        Iterable<UserApplication> userApplications = jdbcAggregateTemplate.insertAll(List.of(u1, u2, u3, u4));

        System.out.println("Insert all users succeed result:");
        userApplications.forEach(System.out::println);

        System.out.println("Find all users results:");
        repository.findAll().forEach(System.out::println);

        System.out.println("Find all users by city results:");
        repository.findAllByCity("hanoi").forEach(System.out::println);

    }


}
