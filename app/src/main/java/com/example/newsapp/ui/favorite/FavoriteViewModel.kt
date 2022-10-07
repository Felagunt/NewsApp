package com.example.newsapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    val FavoriteNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val newsPage = 1

    init {
        getFavoriteNews("")
    }

    private fun getFavoriteNews(query: String) =
        viewModelScope.launch {
            FavoriteNewsLiveData.postValue(Resource.Loading())
            val response = repository.getFavoriteNews(query = query, pageNumber = newsPage)
            if(response.isSuccessful){
                response.body().let { res ->
                    FavoriteNewsLiveData.postValue(Resource.Success(res))
                }
            } else {
                FavoriteNewsLiveData.postValue(Resource.Error(message = response.message()))
            }
        }
}