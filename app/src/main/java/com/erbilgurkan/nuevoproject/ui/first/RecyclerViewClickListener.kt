package com.erbilgurkan.nuevoproject.ui.first

import android.view.View
import com.erbilgurkan.nuevoproject.data.models.FirstModel

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, first: FirstModel)
}