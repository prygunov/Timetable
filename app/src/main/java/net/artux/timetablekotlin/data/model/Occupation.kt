package net.artux.timetablekotlin.data.model

class Occupation(var id:String, var teacher:String, var title:String, var type:String, var date:String, var time:String, var place:String, var groups:List<String>){

    var links:HashMap<String, String>? = null
    var description:String? = null
    var task: Task? = null
}