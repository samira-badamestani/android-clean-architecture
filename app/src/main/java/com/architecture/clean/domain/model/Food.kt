package com.architecture.clean.domain.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



data class Food(
        @SerializedName("title") var title: String,
        @SerializedName("href") var href: String,
        @SerializedName("ingredients") var ingredients: String,
        @SerializedName("thumbnail") var thumbnail: String
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Food> = object : Parcelable.Creator<Food> {
            override fun createFromParcel(source: Parcel): Food = Food(source)
            override fun newArray(size: Int): Array<Food?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(href)
        writeString(ingredients)
        writeString(thumbnail)
    }
}