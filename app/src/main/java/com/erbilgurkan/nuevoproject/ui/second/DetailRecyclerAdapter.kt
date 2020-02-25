package com.erbilgurkan.nuevoproject.ui.second

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.erbilgurkan.nuevoproject.R
import com.erbilgurkan.nuevoproject.data.models.SecondModel
import com.erbilgurkan.nuevoproject.databinding.RecyclerviewDetailBinding

class DetailRecyclerAdapter(
    private val details: List<SecondModel>
) : RecyclerView.Adapter<DetailRecyclerAdapter.DetailViewHolder>() {

    override fun getItemCount() = details.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_detail, parent, false
        )
    )

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.recyclerviewDetailBinding.textViewTitle.text = details[position].name
        holder.recyclerviewDetailBinding.textViewBody.text = details[position].body
    }

    inner class DetailViewHolder(val recyclerviewDetailBinding: RecyclerviewDetailBinding) :
        RecyclerView.ViewHolder(recyclerviewDetailBinding.root)
}