package com.ceiba.ceibaapp.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceibaapp.Constants.Companion.TYPE_HEADER
import com.ceiba.ceibaapp.Constants.Companion.TYPE_ITEM
import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.data.model.ItemPostDataState.Post
import com.ceiba.ceibaapp.data.model.ItemUserDataState
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(private var posts: List<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var user: ItemUserDataState.User

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewHolder) {
            val item = posts[position - 1]
            holder.bind(item)
        } else {
            val holderHeader: UsersAdapter.ViewHolder = holder as UsersAdapter.ViewHolder
            holderHeader.bind(user)
            holderHeader.hideButton()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_ITEM) {
            ViewHolder(layoutInflater.inflate(R.layout.item_post, parent, false))
        } else {
            UsersAdapter.ViewHolder(layoutInflater.inflate(R.layout.item_user, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return posts.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (isPositionHeader(position))
            return TYPE_HEADER

        return TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(post: Post) {
            itemView.txt_title.text = post.title
            itemView.txt_body.text = post.body
        }
    }
}