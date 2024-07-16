package api.model.dto;

import api.model.Address;
import api.model.Gender;
import java.util.Date;

public class CreatePersonRequestBody {

  private String firstname;
  private String lastname;
  private Date birthdate;
  private Gender gender;
  private Address address;

  public CreatePersonRequestBody(
    String firstname,
    String lastname,
    Date birthdate,
    Gender gender,
    Address address
  ) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthdate = birthdate;
    this.gender = gender;
    this.address = address;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Date getBirthdate() {
    return this.birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public Gender getGender() {
    return this.gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Address getAddress() {
    return this.address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
