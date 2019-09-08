package com.example.museumhunt.Model

class User {
    var gender: String? = null
    var age: String? = null
    var location: String? = null

    constructor(name: String, age: String, location: String) {
        this.gender = name
        this.age = age
        this.location = location
    }

    constructor(){

    }

}
