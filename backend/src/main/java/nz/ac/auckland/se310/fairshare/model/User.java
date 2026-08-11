package nz.ac.auckland.se310.fairshare.model;

public class User {

  public enum Country {
    NEW_ZEALAND,
    AUSTRALIA
    // Add more countries as needed
  }

  public enum Currency {
    NZD,
    AUD
    // Add more currencies as needed
  }

  private String username;
  private String password;
  private String email;
  private Country country;
  private Currency currency;

  public User() {}

  public User(String username, String password, String email, Country country, Currency currency) {
    this.username = username;
    this.password = password;
    this.email = email.trim().toLowerCase();
    this.country = country;
    this.currency = currency;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public Country getCountry() {
    return country;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {

    // TODO: Hash the password before storing it (BCrypt)

    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email.trim().toLowerCase();
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
