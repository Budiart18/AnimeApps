package com.aeryz.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.aeryz.core.R
import com.aeryz.core.databinding.ItemAnimeBinding
import com.aeryz.core.domain.model.Anime

class AnimeAdapter: RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

   private var data: List<Anime> = listOf()
   private var onItemClickCallback: OnItemClickCallback? = null

   interface OnItemClickCallback {
      fun onItemClicked(id: Int)
   }

   fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
      this.onItemClickCallback = onItemClickCallback
   }

   class ViewHolder(private val binding: ItemAnimeBinding): RecyclerView.ViewHolder(binding.root) {
      fun bind(data: Anime) {
         binding.apply {
            Glide.with(ivAnime)
               .load(data.images)
               .error(R.drawable.noimage)
               .into(ivAnime)
            tvTitle.text = data.title
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemAnimeBinding.inflate(layoutInflater, parent, false)
      return ViewHolder(binding)
   }

   override fun getItemCount(): Int {
      return data.size
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(data[position])
      holder.itemView.setOnClickListener {
         onItemClickCallback?.onItemClicked(data[position].id ?: 0)
      }
   }

   @SuppressLint("NotifyDataSetChanged")
   fun setData(data: List<Anime>) {
      this.data = data
      notifyDataSetChanged()
   }
}