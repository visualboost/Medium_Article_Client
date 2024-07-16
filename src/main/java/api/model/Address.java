package api.model;

import api.model.Location;

public class Address {

  private String _id;
  private String streetname;
  private int housenumber;
  private Location location;

  public Address(
    String _id,
    String streetname,
    int housenumber,
    Location location
  ) {
    this._id = _id;
    this.streetname = streetname;
    this.housenumber = housenumber;
    this.location = location;
  }

  public String getId() {
    return this._id;
  }

  public void setId(String _id) {
    this._id = _id;
  }

  public String getStreetname() {
    return this.streetname;
  }

  public void setStreetname(String streetname) {
    this.streetname = streetname;
  }

  public int getHousenumber() {
    return this.housenumber;
  }

  public void setHousenumber(int housenumber) {
    this.housenumber = housenumber;
  }

  public Location getLocation() {
    return this.location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }
}
