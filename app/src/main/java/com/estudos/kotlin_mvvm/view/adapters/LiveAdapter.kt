package com.estudos.kotlin_mvvm.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.estudos.kotlin_mvvm.R
import com.estudos.kotlin_mvvm.databinding.ResItemLiveBinding
import com.estudos.kotlin_mvvm.models.LiveModel

class MainAdapter(private val onItemClicked: (LiveModel) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var lives = mutableListOf<LiveModel>()

    fun setLiveList(lives: List<LiveModel>) {

        this.lives = lives.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemLiveBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val live = lives[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return lives.size
    }
}

class MainViewHolder(val binding: ResItemLiveBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(liveModel: LiveModel, onItemClicked: (LiveModel) -> Unit) {

        binding.title.text = liveModel.title
        binding.author.text = liveModel.author

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(liveModel.thumbnailUrl)
            .into(binding.thumbnail)

        itemView.setOnClickListener {
            onItemClicked(liveModel)
        }

    }

}