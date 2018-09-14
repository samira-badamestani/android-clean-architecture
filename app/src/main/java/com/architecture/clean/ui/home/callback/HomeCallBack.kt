package com.architecture.clean.ui.home.callback

import com.architecture.clean.domain.model.Food

interface HomeCallBack{
    fun itemClick(item: Food)
}