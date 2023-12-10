package com.orainjistudio.ugit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orainjistudio.ugit.data.response.detailuser.DetailUser
import com.orainjistudio.ugit.databinding.ItemUserBinding

class GithubUserAdapter
    : ListAdapter<DetailUser, GithubUserAdapter.ViewHolder>(githubUserDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailUser: DetailUser) = with(binding) {
            tvUserNameHome.text = detailUser.login
            tvUserNameHomeDetail.text = detailUser.type

            Glide.with(itemView.context)
                .load(detailUser.avatarUrl)
                .into(imgUserHome)
        }

    }

    companion object {
        val githubUserDiffCallback = object : DiffUtil.ItemCallback<DetailUser>() {
            override fun areItemsTheSame(oldItem: DetailUser, newItem: DetailUser): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DetailUser, newItem: DetailUser): Boolean {
                return oldItem == newItem
            }

        }
    }

}
