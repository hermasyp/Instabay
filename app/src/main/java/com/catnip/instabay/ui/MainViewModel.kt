package com.catnip.instabay.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.instabay.model.SearchResponse
import com.catnip.instabay.service.PixabayApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel(private val service: PixabayApiService) : ViewModel() {

    val searchResult = MutableLiveData<SearchResponse>()
    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Exception>()


    fun searchPost(query: String) {
        loadingState.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = service.searchPhoto(query = query)
                viewModelScope.launch(Dispatchers.Main) {
                    searchResult.postValue(data)
                    loadingState.postValue(false)
                }
            } catch (e: Exception) {
                loadingState.postValue(false)
                errorState.postValue(e)
            }
        }

    }
}