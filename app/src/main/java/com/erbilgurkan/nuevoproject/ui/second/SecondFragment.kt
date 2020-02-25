package com.erbilgurkan.nuevoproject.ui.second

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.erbilgurkan.nuevoproject.R
import com.erbilgurkan.nuevoproject.data.network.RetrofitApi
import com.erbilgurkan.nuevoproject.data.repositories.Repository
import com.erbilgurkan.nuevoproject.ui.first.FirstFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.second_fragment.*

class SecondFragment : Fragment() {

    private lateinit var factory: SecondViewModelFactory
    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = RetrofitApi()
        val repository = Repository(api)
        factory = SecondViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(SecondViewModel::class.java)

        Picasso.get().load(this.arguments!!.getString("image_url")).into(imageView_detail)

        viewModel.getDetails(this.arguments!!.getInt("model", 0))

        viewModel.details.observe(viewLifecycleOwner, Observer { details ->
            recycler_view_detail_list.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = DetailRecyclerAdapter(details)
            }
        })

        imageView_back.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }
    }
}
