package com.catnip.instabay.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.catnip.instabay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter: PostsAdapter by lazy {
        PostsAdapter {
            //
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}