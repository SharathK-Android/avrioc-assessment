package com.avrioc.assessment.ui.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avrioc.assessment.databinding.ArticlesListItemViewBinding
import com.avrioc.assessment.ui.main.data.ArticlesResponse
import com.avrioc.assessment.ui.main.data.ResultsItem
import com.squareup.picasso.Picasso

class ArticlesAdapter(private val items: ArticlesResponse?, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticlesListItemViewBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items?.results?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items?.results?.get(position))


    inner class ViewHolder(private val binding: ArticlesListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultsItem?) {
            binding.apply {
                articleData = item
                imageUrl = item?.media?.getOrNull(0)?.mediaMetadata?.getOrNull(0)?.url
                binding.root.setOnClickListener {
                    itemClickListener.onItemClick(item?.url)
                }
                executePendingBindings()
            }
        }
    }

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, url: String?) {
            url?.let {
                Picasso.get()
                    .load(it)
                    .into(view)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(externalLink: String?)
    }
}