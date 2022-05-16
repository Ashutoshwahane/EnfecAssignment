package xyz.cybernerd404.demo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import xyz.cybernerd404.demo.R
import xyz.cybernerd404.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {

            searchBtn.setOnClickListener {
                userId = userIdET.text.toString()
                if (userId.isEmpty()){
                    Toast.makeText(this@MainActivity, "Please add a userId", Toast.LENGTH_SHORT).show()
                }else{
                    Intent(this@MainActivity, PostActivity::class.java).apply {
                        this.putExtra(getString(R.string.userId), userId.toInt())
                        startActivity(this)
                    }
                }

            }
        }
    }
}