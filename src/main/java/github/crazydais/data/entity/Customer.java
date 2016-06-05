package github.crazydais.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends BaseEntity {

  @Column(name = "firstname", unique = false, nullable = false)
  private String firstName;
  
  @Column(name = "lastname", unique = false, nullable = false)
  private String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
