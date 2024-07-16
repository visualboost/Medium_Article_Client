package api.model;

public enum Gender {
  MALE("MALE"),
  FEMALE("FEMALE");

  private String value;

  Gender(String value) {
    this.value = value;
  }

  public String toString() {
    return this.value;
  }
}
