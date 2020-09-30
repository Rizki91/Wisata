
package com.fahrul.wisata.model.kuliner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelKuliner implements Serializable, Parcelable
{

    @SerializedName("kuliner")
    @Expose
    private List<Kuliner> kuliner = new ArrayList<Kuliner>();

    public final static Creator<ModelKuliner> CREATOR = new Creator<ModelKuliner>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ModelKuliner createFromParcel(Parcel in) {
            return new ModelKuliner(in);
        }

        public ModelKuliner[] newArray(int size) {
            return (new ModelKuliner[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7264364465476192604L;

    protected ModelKuliner(Parcel in) {
        in.readList(this.kuliner, (Kuliner.class.getClassLoader()));
    }

    public ModelKuliner() {
    }

    public List<Kuliner> getKuliner() {
        return kuliner;
    }

    public void setKuliner(List<Kuliner> kuliner) {
        this.kuliner = kuliner;
    }

    public ModelKuliner withKuliner(List<Kuliner> kuliner) {
        this.kuliner = kuliner;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(kuliner);
    }

    public int describeContents() {
        return  0;
    }

}
