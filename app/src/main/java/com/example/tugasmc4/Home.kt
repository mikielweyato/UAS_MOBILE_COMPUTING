package com.example.tugasmc4

import android.content.Intent
import android.os.Bundle
import android.view.View

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmc4.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import  com.google.firebase.database.FirebaseDatabase
import  com.google.firebase.database.DataSnapshot
import  com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var dataRecyclerView: RecyclerView
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var sepatuAdapter: MyAdapter

    private lateinit var listSepatu: MutableList<data>
    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        dataRecyclerView = findViewById(R.id.hasilData)

        dataRecyclerView.setHasFixedSize(true)
        dataRecyclerView.layoutManager = LinearLayoutManager(this@Home)
        binding.myprogressBar.visibility = View.VISIBLE

        listSepatu = ArrayList()
        sepatuAdapter = MyAdapter(this@Home, listSepatu)
        dataRecyclerView.adapter = sepatuAdapter


        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("dsepatu")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Home, error.message, Toast.LENGTH_SHORT).show()
                binding.myprogressBar.visibility = View.INVISIBLE
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listSepatu.clear()
                for (teacherSnapshot in snapshot.children) {
                    val upload = teacherSnapshot.getValue(data::class.java)
                    upload!!.key = teacherSnapshot.key
                    listSepatu.add(upload)
                }
                sepatuAdapter.notifyDataSetChanged()
                binding.myprogressBar.visibility = View.GONE
            }

        })


//        binding.imgLog.setOnClickListener {
//            firebaseAuth.signOut()
//
//            Intent(this,  SignIn::class.java).also {
//                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(it)
//                    }
//
//                    }
//
//
            binding.imgLog.setOnClickListener {
                firebaseAuth.signOut()

                Intent(this,  Login::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }


    }

}



//        private fun loginUser(email: String, password: String) {
//
//            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Intent(this, Bacaan::class.java).also {
//                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        startActivity(it)
//                    }
//                } else {
//                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
//                } }





//        gambar = arrayOf(
//            R.drawable.gambar3,
//            R.drawable.gambar1,
//            R.drawable.gambar7,
//            R.drawable.gambar3,
//            R.drawable.gambar1,
//            R.drawable.gambar7
//        )
//
//        judul = arrayOf(
//            "Nike SB Dunk",
//            "New Balance 550",
//            "Nike Air Jordan",
//            "Nike SB Dunk",
//            "New balance 550",
//            "Nike Air Jordan",
//            "Nike SB Dunk"
//        )
//
//        harga = arrayOf(
//            "Rp1.990.000",
//            "Rp2.990.000",
//            "Rp3.000.000",
//            "Rp1.990.000",
//            "Rp2.990.000",
//            "Rp3.0000.000",
//            "Rp1.990.000"
//        )
//        ulasan = arrayOf(
//            getString(R.string.u_1),
//            getString(R.string.u_2),
//            getString(R.string.u_3),
//            getString(R.string.u_4),
//            getString(R.string.u_5),
//            getString(R.string.u_6)
//        )
//        deskripsi = arrayOf(
//            getString(R.string.d_1),
//            getString(R.string.d_2),
//            getString(R.string.d_3),
//            getString(R.string.d_4),
//            getString(R.string.d_5),
//            getString(R.string.d_6)
//        )
//        newRecyclerView = findViewById(R.id.hasil)
//        newRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        listBacaan = arrayListOf<ItemData>()
//        getShop()




//    private fun getShop() {
//        for (i in gambar.indices) {
//            val dataBacaan = ItemData(gambar[i], judul[i], harga[i])
////            listBacaan.add(dataBacaan)
//            listBacaan.add(dataBacaan)
//        }
//        var adapter = MyAdapter(listBacaan)
//        newRecyclerView.adapter = adapter
//
//        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
//            override fun onItemClick(position: Int) {
//                val intent = Intent(this@Home, Detail::class.java)
//                intent.putExtra("gbr", listBacaan[position].gambar)
//                intent.putExtra("jdl", listBacaan[position].judul)
//                intent.putExtra("hrg", listBacaan[position].hrg)
//                intent.putExtra("uls", ulasan[position])
//                intent.putExtra("desk", deskripsi[position])
//
//                startActivity(intent)
//
//
//            }
//
//        })
//
//    }
