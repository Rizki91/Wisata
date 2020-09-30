
package com.fahrul.wisata.model.wisata;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wisatum implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("gambar_url")
    @Expose
    private String gambarUrl;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    public final static Creator<Wisatum> CREATOR = new Creator<Wisatum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Wisatum createFromParcel(Parcel in) {
            return new Wisatum(in);
        }

        public Wisatum[] newArray(int size) {
            return (new Wisatum[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7064356271163103828L;

    protected Wisatum(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.gambarUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.kategori = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Wisatum() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wisatum withId(int id) {
        this.id = id;
        return this;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Wisatum withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }

    public Wisatum withGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
        return this;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Wisatum withKategori(String kategori) {
        this.kategori = kategori;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
        dest.writeValue(gambarUrl);
        dest.writeValue(kategori);
    }

    public int describeContents() {
        return  0;
    }

}
