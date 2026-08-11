package nz;

import nz.ac.auckland.se310.fairshare.model.UserDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class AppConfig {

  @Bean
  public UserDB userDB() {
    return new UserDB();
  }
}
