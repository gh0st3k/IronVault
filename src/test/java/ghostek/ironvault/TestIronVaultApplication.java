package ghostek.ironvault;

import org.springframework.boot.SpringApplication;

public class TestIronVaultApplication {

    public static void main(String[] args) {
        SpringApplication.from(IronVaultApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
