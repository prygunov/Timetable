package net.artux.timetablekotlin.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OccupationItem {

    @SerializedName("ID")
    @Expose
    val iD: String? = null

    @SerializedName("FirstID")
    @Expose
    val firstID: String? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("start")
    @Expose
    val start: String? = null

    @SerializedName("end")
    @Expose
    val end: String? = null

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("IsNew")
    @Expose
    val isNew: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("NewTask")
    @Expose
    val newTask: String? = null

    @SerializedName("NewResult")
    @Expose
    val newResult: String? = null

    @SerializedName("ControlPointNumber")
    @Expose
    val controlPointNumber: String? = null

    @SerializedName("color")
    @Expose
    val color: String? = null


}