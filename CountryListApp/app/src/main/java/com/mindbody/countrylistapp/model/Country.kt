package com.mindbody.countrylistapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    @field:SerializedName("Code")
    val code: String,
    @field:SerializedName("ID")
    val ID: Int,
    @field:SerializedName("Name")
    val name: String,
    @field:SerializedName("PhoneCode")
    val phoneCode: String
) : Parcelable