
package com.fahrul.wisata.model.tempatibadah;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempatIbadah implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    public final static Creator<TempatIbadah> CREATOR = new Creator<TempatIbadah>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TempatIbadah createFromParcel(Parcel in) {
            return new TempatIbadah(in);
        }

        public TempatIbadah[] newArray(int size) {
            return (new TempatIbadah[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3423391424204222108L;

    protected TempatIbadah(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.jenis = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TempatIbadah() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TempatIbadah withId(int id) {
        this.id = id;
        return this;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public TempatIbadah withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public TempatIbadah withJenis(String jenis) {
        this.jenis = jenis;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public TempatIbadah withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public TempatIbadah withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
        dest.writeValue(jenis);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
    }

    public int describeContents() {
        return  0;
    }

}
