package xyz.cybernerd404.demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.cybernerd404.demo.databinding.PostItemLayoutBinding
import xyz.cybernerd404.demo.model.UserData

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var list: List<UserData> = arrayListOf()

    fun setUserData(response: List<UserData>) {
        this.list = response
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(PostItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {

            this.userName.text = "UserName : ${list[position].userName}"
            this.title.text = list[position].title
            this.body.text = list[position].body
        }



    }


    class ViewHolder(val binding: PostItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


}