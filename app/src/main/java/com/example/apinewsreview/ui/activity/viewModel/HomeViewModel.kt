package com.example.apinewsreview.ui.activity.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apinewsreview.api.ApiManager
import com.example.apinewsreview.pojo.ArticlesItem
import com.example.apinewsreview.pojo.NewsResponse
import com.example.apinewsreview.pojo.SourcesItem
import com.example.apinewsreview.pojo.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val progressLiveData = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    val newLiveData = MutableLiveData<List<ArticlesItem?>?>()

    fun getSources(){
        progressLiveData.value = true
        ApiManager.getApi().getSources(Constants.apiKey,lang = "en",country = "us")
            .enqueue(object :Callback<SourcesResponse>{
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    messageLiveData.value = t.localizedMessage
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressLiveData.value = false
                    if (response.isSuccessful){
                        sourcesLiveData.value = response.body()?.sources
                    }else{
                        messageLiveData.value = response.body()?.message?:""
                    }
                }
            })
    }
    fun getNews(sourcesId: String){
        progressLiveData.value = true
        newLiveData.value = null
        ApiManager.getApi().getNews(Constants.apiKey,"en",sourcesId?:"","")
            .enqueue(object :Callback<NewsResponse>{
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    messageLiveData.value = t.localizedMessage
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressLiveData.value = false
                    if (response.isSuccessful){
                        newLiveData.value = response.body()?.articles
                    }else{
                        messageLiveData.value = response.body()?.message?:""
                    }
                }
            })
    }

}