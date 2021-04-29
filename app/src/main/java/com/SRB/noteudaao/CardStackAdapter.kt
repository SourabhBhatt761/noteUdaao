package com.SRB.noteudaao

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.SRB.noteudaao.databinding.ItemNotesBinding

class CardStackAdapter: RecyclerView.Adapter<CardStackAdapter.MyViewHolder>()  {

    var images = arrayListOf<Int>()

    fun showEverything(image : ArrayList<Int>){
        images = image
    }


    inner class MyViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardStackAdapter.MyViewHolder {
    return MyViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CardStackAdapter.MyViewHolder, position: Int) {
        holder.binding.noteIv.apply {
            setImageResource(images[position])



        }

    }

    override fun getItemCount(): Int = images.size
}