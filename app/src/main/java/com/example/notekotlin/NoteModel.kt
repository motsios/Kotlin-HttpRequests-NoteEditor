package com.example.notekotlin

import java.io.Serializable

class NoteModel:Serializable {
    var title:String?=null
    var content:String?=null
    var createdAt:String?=null
    var id:String?= null

    fun getIds(): String {
        return id.toString()
    }
    fun setIds(id:String){
        this.id=id
    }
    fun getTitles():String{
        return title.toString()
    }
    fun setTitles(title:String){
        this.title=title
    }
    fun getContents():String{
        return content.toString()
    }
    fun setContents(content:String){
        this.content=content
    }
    fun getCreatedAts():String{
        return createdAt.toString()
    }
    fun setCreatedAts(createdAt:String){
        this.createdAt=createdAt
    }
}
