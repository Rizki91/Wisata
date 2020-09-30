
package com.fahrul.wisata.model.kuliner.detail;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelKulinerDetail implements Serializable, Parcelable
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
    @SerializedName("nomor_telp")
    @Expose
    private String nomorTelp;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    public final static Creator<ModelKulinerDetail> CREATOR = new Creator<ModelKulinerDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ModelKulinerDetail createFromParcel(Parcel in) {
            return new ModelKulinerDetail(in);
        }

        public ModelKulinerDetail[] newArray(int size) {
            return (new ModelKulinerDetail[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8386253024964081619L;

    protected ModelKulinerDetail(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.jamBukaTutup = ((String) in.readValue((String.class.getClassLoader())));
        this.kordinat = ((String) in.readValue((String.class.getClassLoader())));
        this.gambarUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.kategori = ((String) in.readValue((String.class.getClassLoader())));
        this.nomorTelp = ((String) in.readValue((String.class.getClassLoader())));
        this.deskripsi = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ModelKulinerDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelKulinerDetail withId(int id) {
        this.id = id;
        return this;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ModelKulinerDetail withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public ModelKulinerDetail withAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public String getJamBukaTutup() {
        return jamBukaTutup;
    }

    public void setJamBukaTutup(String jamBukaTutup) {
        this.jamBukaTutup = jamBukaTutup;
    }

    public ModelKulinerDetail withJamBukaTutup(String jamBukaTutup) {
        this.jamBukaTutup = jamBukaTutup;
        return this;
    }

    public String getKordinat() {
        return kordinat;
    }

    public void setKordinat(String kordinat) {
        this.kordinat = kordinat;
    }

    public ModelKulinerDetail withKordinat(String kordinat) {
        this.kordinat = kordinat;
        return this;
    }

    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }

    public ModelKulinerDetail withGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
        return this;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public ModelKulinerDetail withKategori(String kategori) {
        this.kategori = kategori;
        return this;
    }

    public String getNomorTelp() {
        return nomorTelp;
    }

    public void setNomorTelp(String nomorTelp) {
        this.nomorTelp = nomorTelp;
    }

    public ModelKulinerDetail withNomorTelp(String nomorTelp) {
        this.nomorTelp = nomorTelp;
        return this;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ModelKulinerDetail withDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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
        dest.writeValue(nomorTelp);
        dest.writeValue(deskripsi);
    }

    public int describeContents() {
        return  0;
    }

}
