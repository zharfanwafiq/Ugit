package com.orainjistudio.ugit.data.response.detailuser

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ListUser(

	@field:SerializedName("Response")
	val response: List<DetailUser?>? = null
) : Parcelable