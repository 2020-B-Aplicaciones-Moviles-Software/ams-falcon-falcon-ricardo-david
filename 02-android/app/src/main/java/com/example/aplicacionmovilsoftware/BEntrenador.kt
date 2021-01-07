package com.example.aplicacionmovilsoftware

import android.os.Parcel
import android.os.Parcelable

/*class BEntrenador {
    var nombre: String
    var descripcion: String


    constructor(nombre: String, descripcion: String) {
        this.nombre = nombre
        this.descripcion = descripcion
    }

    override fun toString(): String {
        return "${nombre} ${descripcion}"
    }
} */
//PARCELABLE

class BEntrenador(
      //  protected  var nombre:String,
    //    protected var descripcion:String

     val nombre: String?,
     val descripcion: String?,
      val liga: DLiga?
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
    parcel.readParcelable(DLiga::class.java.classLoader)) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeString(descripcion)
        dest?.writeParcelable(liga,flags)
    }

    override fun toString(): String {
        return "${nombre} ${descripcion}"
    }

    override fun describeContents(): Int {
      return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}