package rosa.ribeiro.jonas.customerdomain;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "rosa.ribeiro.jonas.customerdomain.repository")
@EntityScan(basePackages = "rosa.ribeiro.jonas.customerdomain.model")
public class TestConfig {
}