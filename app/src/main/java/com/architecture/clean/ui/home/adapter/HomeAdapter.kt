package com.architecture.clean.ui.home.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.architecture.clean.BR.click
import com.architecture.clean.BR.item
import com.architecture.clean.databinding.FoodItemRowBinding
import com.architecture.clean.domain.model.Food
import com.architecture.clean.ui.DataBindingViewHolder
import com.architecture.clean.ui.home.callback.HomeCallBack

class HomeAdapter(
        private var items: ArrayList<Food> = arrayListOf<Food>(),
        private val callback: HomeCallBack
) : RecyclerView.Adapter<HomeAdapter.SimpleHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding  = FoodItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<Food>(dataBinding)  {
        override fun onBind(t: Food): Unit = with(t) {
            dataBinding.setVariable(item,t)
            dataBinding.setVariable(click,callback)
        }
    }

    fun add(list: ArrayList<Food>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}