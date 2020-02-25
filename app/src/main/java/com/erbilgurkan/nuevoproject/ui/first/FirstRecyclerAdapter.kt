package com.erbilgurkan.nuevoproject.ui.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.erbilgurkan.nuevoproject.R
import com.erbilgurkan.nuevoproject.data.models.FirstModel
import com.erbilgurkan.nuevoproject.databinding.RecyclerviewFirstBinding

class FirstRecyclerAdapter(
    private val first: List<FirstModel>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<FirstRecyclerAdapter.FirstViewHolder>() {

    override fun getItemCount() = first.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FirstViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_first, parent, false
        )
    )

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        holder.recyclerviewFirstBinding.firstView = first[position]
        holder.recyclerviewFirstBinding.root.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerviewFirstBinding.root, first[position])
        }
    }

    inner class FirstViewHolder(val recyclerviewFirstBinding: RecyclerviewFirstBinding) :
        RecyclerView.ViewHolder(recyclerviewFirstBinding.root)
}