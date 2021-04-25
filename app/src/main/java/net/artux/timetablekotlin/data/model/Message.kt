package net.artux.timetablekotlin.data.model

data class Message(

    var ID: String? = null,
    var Fio: String? = null,
    var Text: String? = null,
    var StoredFileID: Any? = null,
    var StoredFileName: Any? = null,
    var Date: String? = null,
    var IsNew: String? = null
)