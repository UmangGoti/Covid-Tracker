package com.example.covidtracker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseOt(
	val statewise: List<StatewiseItem>
) : Parcelable

@Parcelize
data class StatewiseItem(
	val recovered: String ,
	val active: String ,
	val state: String? = null,
	val confirmed: String ,
	val deaths: String ,
	val lastupdatedtime: String? = null
) : Parcelable
