package com.uangteman.faisalramd.mvp.contactapp.ui.contact

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.uangteman.faisalramd.mvp.contactapp.R
import com.uangteman.faisalramd.mvp.contactapp.models.Contact

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class ContactAdapter(private val context: Context, private val list: MutableList<Contact>,
                     fragment: Fragment): RecyclerView.Adapter<ContactAdapter.ListViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = fragment as onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var post = list[position]

        // holder!!.bind(post)
        holder.title!!.setText(post.firstName + " " + post.lastName)
        holder.body!!.setText("Age : " + post.age + "\n")

        holder.layout!!.setOnClickListener {
            listener.itemDetail(post.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ListViewHolder(itemView)
    }

    fun removeAt(position: Int) {
        listener.itemRemoveClick(list[position])
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val body = itemView.findViewById<TextView>(R.id.item_body)

        fun bind(item: Contact) {
            // title = item.post
            // body etc.
        }
    }

    interface onItemClickListener {
        fun itemRemoveClick(contact: Contact)
        fun itemDetail(postId : String)
    }
}