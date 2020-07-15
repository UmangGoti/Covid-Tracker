package com.example.covidtracker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseOt(
	val statewise: List<StatewiseItem>
) : Parcelable

@Parcelize
data class StatewiseItem(
	val recovered: String? = null,
	val active: String? = null,
	val state: String? = null,
	val confirmed: String? = null,
	val deaths: String? = null,
	val lastupdatedtime: String? = null
) : Parcelable
