package com.architecture.clean.ui.home

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.model.response.ErrorModel
import com.architecture.clean.domain.model.response.ErrorResponse
import com.architecture.clean.domain.model.response.SuccessResponse
import com.architecture.clean.domain.model.response.UseCaseResponse
import com.architecture.clean.domain.usecase.GetHomeUseCase
import com.architecture.clean.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getHomeUseCase: GetHomeUseCase) : BaseViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName
    val homeData: MutableLiveData<FoodDto> by lazy { MutableLiveData<FoodDto>() }
    val error : MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    init {
        getHomeUseCase.execute(compositeDisposable, this::getHomeResponse)
    }
    public fun getHomeResponse(response: UseCaseResponse<FoodDto>) {
        Log.d(TAG, "getHomeResponse() called  with: response = [$response]")
        when (response) {
            is SuccessResponse -> homeData.value = response.value
            is ErrorResponse -> error.value=response.error
        }
    }
}