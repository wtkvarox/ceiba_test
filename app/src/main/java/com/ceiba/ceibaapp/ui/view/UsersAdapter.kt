package com.ceiba.ceibaapp.ui.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.data.model.ItemUserDataState.User
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun filterList(filteredNames: ArrayList<User>) {
        users = filteredNames
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: User) {
            itemView.txt_name.text = user.name
            itemView.txt_phone.text = user.phone
            itemView.txt_email.text = user.email

            itemView.item.setOnClickListener {
                val mIntent = Intent(it.context, DetailViewActivity::class.java)
                mIntent.putExtra("ID", user.id)
                it.context.startActivity(mIntent)
            }
        }

        fun hideButton() {
            itemView.txt_posts.visibility = View.GONE
        }
    }
}