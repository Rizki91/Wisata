
package com.fahrul.wisata.model.wisata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelWisata implements Serializable, Parcelable
{

    @SerializedName("wisata")
    @Expose
    private List<Wisatum> wisata = new ArrayList<Wisatum>();
    public final static Creator<ModelWisata> CREATOR = new Creator<ModelWisata>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ModelWisata createFromParcel(Parcel in) {
            return new ModelWisata(in);
        }

        public ModelWisata[] newArray(int size) {
            return (new ModelWisata[size]);
        }

    }
    ;
    private final static long serialVersionUID = -5370188332619451083L;

    protected ModelWisata(Parcel in) {
        in.readList(this.wisata, (com.fahrul.wisata.model.wisata.Wisatum.class.getClassLoader()));
    }

    public ModelWisata() {
    }

    public List<Wisatum> getWisata() {
        return wisata;
    }

    public void setWisata(List<Wisatum> wisata) {
        this.wisata = wisata;
    }

    public ModelWisata withWisata(List<Wisatum> wisata) {
        this.wisata = wisata;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(wisata);
    }

    public int describeContents() {
        return  0;
    }

}
