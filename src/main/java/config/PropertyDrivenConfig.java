package config;

import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import java.util.Map;

@Configuration
@Profile("default")
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

  @Value("${dao.target:dao}")
  private String target;

  @Bean(name = "selectedDao")
  @Primary
  @DependsOn("propertySourcesPlaceholderConfigurer")
  public IDao selectedDao(Map<String, IDao> candidates) {
    IDao bean = candidates.get(target);
    if (bean == null) {
      throw new IllegalArgumentException("Implémentation inconnue: " + target + " (dao|dao2|daoFile|daoApi)");
    }
    return bean;
  }

  // Résolution des placeholders @Value sans Spring Boot
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
