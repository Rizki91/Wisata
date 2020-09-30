
package com.fahrul.wisata.model.hotel;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("nomor_telp")
    @Expose
    private String nomorTelp;
    @SerializedName("kordinat")
    @Expose
    private String kordinat;
    @SerializedName("gambar_url")
    @Expose
    private String gambarUrl;
    public final static Creator<Hotel> CREATOR = new Creator<Hotel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        public Hotel[] newArray(int size) {
            return (new Hotel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1097204898681949738L;

    protected Hotel(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.nomorTelp = ((String) in.readValue((String.class.getClassLoader())));
        this.kordinat = ((String) in.readValue((String.class.getClassLoader())));
        this.gambarUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Hotel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel withId(int id) {
        this.id = id;
        return this;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Hotel withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Hotel withAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public String getNomorTelp() {
        return nomorTelp;
    }

    public void setNomorTelp(String nomorTelp) {
        this.nomorTelp = nomorTelp;
    }

    public Hotel withNomorTelp(String nomorTelp) {
        this.nomorTelp = nomorTelp;
        return this;
    }

    public String getKordinat() {
        return kordinat;
    }

    public void setKordinat(String kordinat) {
        this.kordinat = kordinat;
    }

    public Hotel withKordinat(String kordinat) {
        this.kordinat = kordinat;
        return this;
    }

    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }

    public Hotel withGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
        dest.writeValue(alamat);
        dest.writeValue(nomorTelp);
        dest.writeValue(kordinat);
        dest.writeValue(gambarUrl);
    }

    public int describeContents() {
        return  0;
    }

}
