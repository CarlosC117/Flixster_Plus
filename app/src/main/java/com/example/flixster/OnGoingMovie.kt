package com.example.flixster

import com.google.gson.annotations.SerializedName

class OnGoingMovie {
    @SerializedName("title")
    var titleMovie: String? = null

    @SerializedName("overview")
    var descriptionMovie: String? = null

    @SerializedName("poster_path")
    var imageMovie: String? = null
}