
package com.fahrul.wisata.model.kuliner;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kuliner implements Serializable, Parcelable
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
    @SerializedName("jam_buka_tutup")
    @Expose
    private String jamBukaTutup;
    @SerializedName("kordinat")
    @Expose
    private String kordinat;
    @SerializedName("gambar_url")
    @Expose
    private String gambarUrl;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    public final static Creator<Kuliner> CREATOR = new Creator<Kuliner>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Kuliner createFromParcel(Parcel in) {
            return new Kuliner(in);
        }

        public Kuliner[] newArray(int size) {
            return (new Kuliner[size]);
        }

    }
    ;
    private final static long serialVersionUID = 209603327007604793L;

    protected Kuliner(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.jamBukaTutup = ((String) in.readValue((String.class.getClassLoader())));
        this.kordinat = ((String) in.readValue((String.class.getClassLoader())));
        this.gambarUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.kategori = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Kuliner() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kuliner withId(int id) {
        this.id = id;
        return this;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Kuliner withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Kuliner withAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public String getJamBukaTutup() {
        return jamBukaTutup;
    }

    public void setJamBukaTutup(String jamBukaTutup) {
        this.jamBukaTutup = jamBukaTutup;
    }

    public Kuliner withJamBukaTutup(String jamBukaTutup) {
        this.jamBukaTutup = jamBukaTutup;
        return this;
    }

    public String getKordinat() {
        return kordinat;
    }

    public void setKordinat(String kordinat) {
        this.kordinat = kordinat;
    }

    public Kuliner withKordinat(String kordinat) {
        this.kordinat = kordinat;
        return this;
    }

    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }

    public Kuliner withGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
        return this;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Kuliner withKategori(String kategori) {
        this.kategori = kategori;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
        dest.writeValue(alamat);
        dest.writeValue(jamBukaTutup);
        dest.writeValue(kordinat);
        dest.writeValue(gambarUrl);
        dest.writeValue(kategori);
    }

    public int describeContents() {
        return  0;
    }

}
