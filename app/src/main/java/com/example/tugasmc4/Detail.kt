package com.example.tugasmc4

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugasmc4.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_detail)

//        val gambar : ImageView = findViewById(R.id.dimg)
//        val judul : TextView = findViewById(R.id.tv_judul)
//        val harga : TextView = findViewById(R.id.tv_harga)
//        val ulasan : TextView = findViewById(R.id.tv_ulasan)
//        val deskripsi : TextView = findViewById(R.id.tv_deskripsi)
//
//
//        val bundle: Bundle?= intent.extras
//        val bJudul = bundle!!.getString("jdl")
//        val bGambar = bundle.getInt("gbr")
//        val bHarga = bundle.getString("hrg")
//        val bUlasan = bundle.getString("uls")
//        val bDeskripsi = bundle.getString("desk")
//
//        gambar.setImageResource(bGambar)
//        judul.text = bJudul
//        harga.text = bHarga
//        ulasan.text = bUlasan
//        deskripsi.text = bDeskripsi
//        mIntent.putExtra("DESKT", desk)
//        mIntent.putExtra("HARGAT", harga)
//        mIntent.putExtra("JUDULT", judul)
//        mIntent.putExtra("ULASANT", ulasan)
//        mIntent.putExtra("IMGT", img)

        val intess = intent

        var  judulS = intess.getStringExtra("JUDULT")
        var  hargaS = intess.getStringExtra("HARGAT")
        var  imgS = intess.getStringExtra("IMGT")
        var  ulasanS = intess.getStringExtra("ULASANT")
        var  deskripsiS = intess.getStringExtra("DESKT")


        binding.tvJudul.text = judulS
        binding.tvHarga.text = hargaS
        binding.dimg.loadImage(imgS)
        binding.tvDeskripsi.text = deskripsiS
        binding.tvUlasan.text = ulasanS

    }
}