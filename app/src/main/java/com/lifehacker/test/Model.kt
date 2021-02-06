package com.lifehacker.test

public class Model{
    lateinit var id:String
    lateinit var name:String
    lateinit var email:String
    lateinit var address:String
    lateinit var gender:String




    constructor(id: String,name:String,email:String,address:String,gender:String) {
        this.id = id
        this.name = name
        this.email = email
        this.address = address
        this.gender = gender
    }

    constructor()


}