package com.erbilgurkan.nuevoproject.ui.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.erbilgurkan.nuevoproject.R
import com.erbilgurkan.nuevoproject.data.models.FirstModel
import com.erbilgurkan.nuevoproject.data.repositories.Repository
import com.erbilgurkan.nuevoproject.data.network.RetrofitApi
import com.erbilgurkan.nuevoproject.ui.second.SecondFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.first_fragment.*


class FirstFragment : Fragment() {

    private lateinit var factory: FirstViewModelFactory
    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = RetrofitApi()
        val repository = Repository(api)
        factory = FirstViewModelFactory(
            repository
        )
        viewModel = ViewModelProviders.of(this, factory).get(FirstViewModel::class.java)

        viewModel.getFirstViews()

        val clickListener = object : RecyclerViewClickListener {
            override fun onRecyclerViewItemClick(view: View, first: FirstModel) {
                val args = Bundle()
                args.putInt("model", first.id)
                args.putString("image_url", first.url)
                val fragment = SecondFragment()
                fragment.arguments = args
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }

        viewModel.firstViews.observe(viewLifecycleOwner, Observer { firstItem ->
            Picasso.get().load(firstItem[0].url).into(imageView)
        })

        viewModel.firstViews.observe(viewLifecycleOwner, Observer { firstViews ->
            recycler_view_first_views.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = FirstRecyclerAdapter(firstViews, clickListener)
            }
        })
    }
}
