package com.ceiba.ceibaapp.ui.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.data.model.ItemUserDataState
import com.ceiba.ceibaapp.databinding.ActivityDetailViewBinding
import com.ceiba.ceibaapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailViewBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var rvPosts: RecyclerView
    private lateinit var user: ItemUserDataState.User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val idUser = intent.getIntExtra("ID", 0)
        title = getString(R.string.tittle_app)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        configSwipeRefresh(idUser)
        rvPosts = binding.recyclerViewPosts

        userViewModel.userPostsModel.observe(this) {
            val postAdapter = PostsAdapter(it)
            rvPosts.setHasFixedSize(true)
            rvPosts.layoutManager = LinearLayoutManager(applicationContext)
            postAdapter.user = user
            rvPosts.adapter = postAdapter
        }

        userViewModel.userModel.observe(this){
            user = it
            userViewModel.getUserPosts(it)
        }

        userViewModel.isLoading.observe(this) {
            binding.swipe.isRefreshing = it
        }

        userViewModel.getUser(idUser)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun configSwipeRefresh(idUser: Int) {
        binding.swipe.setColorSchemeColors(
            getColor(R.color.primaryColor),
            getColor(R.color.secondaryColor)
        )

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            userViewModel.getUser(idUser)
        }
    }
}