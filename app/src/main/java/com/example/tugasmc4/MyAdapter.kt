package com.example.tugasmc4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter (var mContext: Context, var sepatuList: List<data>):
    RecyclerView.Adapter<MyAdapter.ListViewHolder>(){


    inner class ListViewHolder( var v :View): RecyclerView.ViewHolder(v){

        val  imgTT = v.findViewById<ImageView>(R.id.img_item_photo)
        val  judulTT = v.findViewById<TextView>(R.id.tv_jdl)
        val  hargaT = v.findViewById<TextView>(R.id.harga)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.item_data,parent, false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return sepatuList.size
    }
    override fun onBindViewHolder(holder: MyAdapter.ListViewHolder, position: Int) {
        val newList = sepatuList[position]


        holder.imgTT.loadImage(newList.imgurl)
        holder.judulTT.text = newList.judul
        holder.hargaT.text = newList.harga
        holder.v.setOnClickListener {

            val desk = newList.deskripsi
            val harga = newList.harga
            val img = newList.imgurl
            val ulasan = newList.ulasan
            val judul = newList.judul

            val mIntent = Intent(mContext, Detail::class.java)
            mIntent.putExtra("DESKT", desk)
            mIntent.putExtra("HARGAT", harga)
            mIntent.putExtra("JUDULT", judul)
            mIntent.putExtra("ULASANT", ulasan)
            mIntent.putExtra("IMGT", img)
            mContext.startActivity(mIntent)
        }

    }







//  private lateinit var mListener: onItemClickListener

    //  interface  onItemClickListener{
//      fun onItemClick(position: Int)
//  }
//
//    fun setOnItemClickListener(listener: onItemClickListener){
//        mListener = listener
//    }
//
//    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
//        val gambar:ImageView = itemView.findViewById(R.id.img)
//        val judul:TextView = itemView.findViewById(R.id.jdl)
//        val ayat:TextView = itemView.findViewById(R.id.ayt)
//
//        init {
//            itemView.setOnClickListener {
//                listener.onItemClick(adapterPosition)
//            }
//    }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.perdata,parent, false)
//        return MyViewHolder(itemView, mListener)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = namaList[position]
//        holder.gambar.setImageResource(currentItem.img)
//        holder.judul.text = currentItem.judul
//        holder.ayat.text = currentItem.ayat
//
//    }
//
//    override fun getItemCount(): Int {
//        return namaList.size
//    }


}

