
package com.fahrul.wisata.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelHotel implements Serializable, Parcelable
{

    @SerializedName("hotel")
    @Expose
    private List<Hotel> hotel = new ArrayList<Hotel>();
    public final static Creator<ModelHotel> CREATOR = new Creator<ModelHotel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ModelHotel createFromParcel(Parcel in) {
            return new ModelHotel(in);
        }

        public ModelHotel[] newArray(int size) {
            return (new ModelHotel[size]);
        }

    }
    ;
    private final static long serialVersionUID = -6482557522195556270L;

    protected ModelHotel(Parcel in) {
        in.readList(this.hotel, (com.fahrul.wisata.model.hotel.Hotel.class.getClassLoader()));
    }

    public ModelHotel() {
    }

    public List<Hotel> getHotel() {
        return hotel;
    }

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }

    public ModelHotel withHotel(List<Hotel> hotel) {
        this.hotel = hotel;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(hotel);
    }

    public int describeContents() {
        return  0;
    }

}
