package com.example.afifa123.nearme.Model

class Result {

    var name:String ?= null
    var icon:String ?= null
    var geometry:Geometry ?= null

    var photos:Array<Photo> ?= null
    var id:String ?= null
    var place_id:String ?= null
    var plus_code:PlusCode ?= null
    var price_level:Int ?= 0
    var rating:Double ?= 0.0
    var reference:String ?= null
    var scope:String ?= null
    var types:Array<String> ?= null
    var user_ratings_total:Int ?= 0
    var vicinity:String ?= null
    var opening_hours:OpeningHours ?= null

}