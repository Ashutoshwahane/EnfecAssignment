package xyz.cybernerd404.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.cybernerd404.demo.R
import xyz.cybernerd404.demo.adapter.PostAdapter
import xyz.cybernerd404.demo.databinding.ActivityPostBinding
import xyz.cybernerd404.demo.model.UserData
import xyz.cybernerd404.demo.util.debug


/**
 * show the list of post with given userId
 * post with 3 parameters title, body and username
 *
 * */

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var userDataList: MutableList<UserData>
    private lateinit var adapter: PostAdapter
    var userId = 0
    var userName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userDataList = arrayListOf()
        adapter = PostAdapter()
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]

        binding.apply {
            postRV.layoutManager = LinearLayoutManager(this@PostActivity)
            postRV.adapter = adapter
        }

        /** Getting userId from the previous activity using intent*/
        val intent = intent.extras
        intent?.let {
            userId = it.getInt(getString(R.string.userId))
        }

        getUserName()
        setUserData()


    }

    /**
     * getting posts data and creating a seprate list for the with title, username and body as per the requirement and setting it on recyclerView adapter.
     * */
    private fun setUserData() {
        viewModel.postLiveData.observe(this) {
            it.forEach { posts ->
                if (posts.userId == userId){
                    val userData = UserData()
                    userData.userName = userName
                    userData.body = posts.body
                    userData.title = posts.title
                    userDataList.add(userData)
                }
            }
            adapter.setUserData(userDataList)
        }

    }

    /** getting the userName using userId from User API*/
    private fun getUserName() {
        viewModel.userLiveData.observe(this) {
            it.forEach { user ->
                if (user.id == userId){
                    userName = user.name
                    return@forEach
                }
            }
        }
    }

}