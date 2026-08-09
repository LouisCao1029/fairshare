package nz.ac.auckland.se310.fairshare;

public class UserProfile {
  
  private int age;
  private String email;
  private String password;
  private String country;
  private String currency;

  //
  //
  // Constructor and default constructor
  //
  //

  public UserProfile(int age, String password, String email, String country, String currency) {
    this.age = age;
    this.email = email;
    this.password = password;
    this.country = country;
    this.currency = currency;
  }

  public UserProfile() {}

  //
  //
  // Getters and Setters
  //
  //

  public int getAge() {
    return age;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getCountry() {
    return country;
  }

  public String getCurrency() {
    return currency;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
