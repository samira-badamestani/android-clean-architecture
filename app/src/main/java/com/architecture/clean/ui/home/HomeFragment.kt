package com.architecture.clean.ui.home

import android.app.ActivityOptions
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.architecture.clean.R
import com.architecture.clean.databinding.FragmentHomeBinding
import com.architecture.clean.domain.model.Food
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.ui.detail.DetailActivity
import com.architecture.clean.ui.home.adapter.HomeAdapter
import com.architecture.clean.ui.home.callback.HomeCallBack
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.food_item_row.view.*
import android.util.Pair as UtilPair


class  HomeFragment: DaggerFragment(), HomeCallBack {
    private val TAG: String = HomeFragment::class.java.simpleName
    companion object {
        val FRAGMENT_NAME: String = HomeFragment::class.java.name
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by lazy { ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java) }
    val adapter : HomeAdapter by lazy { HomeAdapter(arrayListOf(),this) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentHomeBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel=viewModel
        lifecycle.addObserver(viewModel)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            homeData.observe(this@HomeFragment, Observer {
                initView(it)
            }
            )
            error.observe(this@HomeFragment, Observer {
                progressBar_home.visibility= View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun initView(it: FoodDto?) {
        rv_main_home.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_main_home.adapter = adapter
        progressBar_home.visibility=View.GONE
        if (it!!.results.isNotEmpty()) {
            adapter.clear()
            adapter.add(it.results)

        }else{
            Toast.makeText(context, context?.getString(R.string.empty_list), android.widget.Toast.LENGTH_LONG).show()
        }
    }

    override fun itemClick(item: Food) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("food",item)
        val p1 = UtilPair.create<View, String>(rv_main_home.img_icon, "image")
        val p2 = UtilPair.create<View, String>(rv_main_home.header, "profile")
        val options = ActivityOptions.makeSceneTransitionAnimation(activity,
               p1,p2)


        startActivity(intent, options.toBundle())
    }
}