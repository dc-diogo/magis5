package magis.stockchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "magis.stockchallenge")
@EntityScan(basePackages = "magis.stockchallenge")
public class StockChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockChallengeApplication.class, args);
    }

}
