package fr.drivy.malik.app.model

import android.os.Parcel
import android.os.Parcelable


data class Car(
    val brand: String,
    val model: String,
    val price_per_day: Int,
    val rating: Rating,
    val picture_url: String,
    val owner: Owner
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readParcelable<Rating>(Rating::class.java.classLoader),
        source.readString(),
        source.readParcelable<Owner>(Owner::class.java.classLoader)
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(brand)
            writeString(model)
            writeInt(price_per_day)
            writeParcelable(rating, 0)
            writeString(picture_url)
            writeParcelable(owner, 0)
        }
    }

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Car> = object : Parcelable.Creator<Car> {
            override fun createFromParcel(source: Parcel): Car = Car(source)
            override fun newArray(size: Int): Array<Car?> = arrayOfNulls(size)
        }
    }
}

data class Owner(
    val name: String,
    val picture_url: String,
    val rating: Rating
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readParcelable<Rating>(Rating::class.java.classLoader)
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(name)
            writeString(picture_url)
            writeParcelable(rating, 0)
        }
    }

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Owner> = object : Parcelable.Creator<Owner> {
            override fun createFromParcel(source: Parcel): Owner = Owner(source)
            override fun newArray(size: Int): Array<Owner?> = arrayOfNulls(size)
        }
    }
}

data class Rating(
    val average: Double,
    val count: Int
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readDouble(),
        source.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeDouble(average)
            writeInt(count)
        }
    }

    override fun describeContents() = 0

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Rating> = object : Parcelable.Creator<Rating> {
            override fun createFromParcel(source: Parcel): Rating = Rating(source)
            override fun newArray(size: Int): Array<Rating?> = arrayOfNulls(size)
        }
    }
}
