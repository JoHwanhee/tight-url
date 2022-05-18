package ga.tight.shortenurl;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class ShortenUrlApplicationTest {
    @Container
    static GenericContainer mySQLContainer = new GenericContainer("mysql:5.7")
            .withEnv("MYSQL_DB","shortenurl");

}