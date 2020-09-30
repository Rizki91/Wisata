
package com.fahrul.wisata.model.tempatibadah;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTempatIbadah implements Serializable, Parcelable
{

    @SerializedName("tempat_ibadah")
    @Expose
    private List<TempatIbadah> tempatIbadah = new ArrayList<TempatIbadah>();
    public final static Creator<ModelTempatIbadah> CREATOR = new Creator<ModelTempatIbadah>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ModelTempatIbadah createFromParcel(Parcel in) {
            return new ModelTempatIbadah(in);
        }

        public ModelTempatIbadah[] newArray(int size) {
            return (new ModelTempatIbadah[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7412002326790637197L;

    protected ModelTempatIbadah(Parcel in) {
        in.readList(this.tempatIbadah, (com.fahrul.wisata.model.tempatibadah.TempatIbadah.class.getClassLoader()));
    }

    public ModelTempatIbadah() {
    }

    public List<TempatIbadah> getTempatIbadah() {
        return tempatIbadah;
    }

    public void setTempatIbadah(List<TempatIbadah> tempatIbadah) {
        this.tempatIbadah = tempatIbadah;
    }

    public ModelTempatIbadah withTempatIbadah(List<TempatIbadah> tempatIbadah) {
        this.tempatIbadah = tempatIbadah;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(tempatIbadah);
    }

    public int describeContents() {
        return  0;
    }

}
