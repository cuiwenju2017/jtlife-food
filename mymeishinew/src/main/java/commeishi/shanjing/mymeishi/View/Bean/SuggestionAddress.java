package commeishi.shanjing.mymeishi.View.Bean;

import android.os.Parcel;

import java.io.Serializable;


/**
 * Created by Administrator on 2017/8/31.
 */

public class SuggestionAddress implements Serializable {
    private String name;
    private String streetMessage;
    private double latitude;
    private double longitude;
    private String city;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public SuggestionAddress(String name, String streetMessage, double latitude , double longitude) {
        this.name = name;
        this.streetMessage = streetMessage;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public SuggestionAddress(String name, String streetMessage,String city) {
        this.name = city;
        this.streetMessage = streetMessage;


    }

    public SuggestionAddress(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    protected SuggestionAddress(Parcel in) {
        name = in.readString();
        streetMessage = in.readString();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetMessage() {
        return streetMessage;
    }

    public void setStreetMessage(String streetMessage) {
        this.streetMessage = streetMessage;
    }


    @Override
    public String toString() {
        return "SuggestionAddress{" +
                "name='" + name + '\'' +
                ", streetMessage='" + streetMessage + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }



}
