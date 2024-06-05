package com.example.tugasmc4

//import com.google.firebase.database.Exclude
import com.google.firebase.database.Exclude
data class data(

    val deskripsi : String? = null,

    val harga : String? = null,
    val imgurl : String? = null,

    val judul : String? = null,
    val ulasan : String? = null,
    
    @get:Exclude
    @set:Exclude

    var key:String? = null


)
