package nz.ac.auckland.se310.fairshare;

public class UserProfile {
  private String username;
  private String password;
  private String email;
  private String country;
  private String currency;

  //
  //
  // Constructor and default constructor
  //
  //

  public UserProfile(String username, String password, String email, String country, String currency) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.country = country;
    this.currency = currency;
  }

  public UserProfile() {}

  //
  //
  // Getters and Setters
  //
  //

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getCountry() {
    return country;
  }

  public String getCurrency() {
    return currency;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
