package xyz.cybernerd404.demo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.cybernerd404.demo.model.PostModel
import xyz.cybernerd404.demo.model.UserModel
import xyz.cybernerd404.demo.network.TestApi
import xyz.cybernerd404.demo.util.debug

class PostViewModel: ViewModel() {
    var postLiveData: MutableLiveData<PostModel> = MutableLiveData()
    var userLiveData: MutableLiveData<UserModel> = MutableLiveData()

    init {
        getUser()
        getPosts()
    }

    /** using coroutine scope to make an API call*/
    fun getPosts() = viewModelScope.launch {
        val data = TestApi().getPost()
        if (data.isSuccessful){
            data?.let {
                debug("response : ${data.body()}")
                postLiveData.value = it.body()
            }
        }

    }

    fun getUser() = viewModelScope.launch {
        val data = TestApi().getUser()
        if (data.isSuccessful){
            data?.let {
                debug("response : ${data.body()}")
                userLiveData.value = it.body()
            }
        }
    }

}