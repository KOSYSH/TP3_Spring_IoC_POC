package config;

import dao.DaoImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("primary")
public class DaoPrimaryConfig {
  @Bean(name = "dao2")
  @Primary
  public DaoImpl2 primaryDao() {
    return new DaoImpl2();
  }
}
