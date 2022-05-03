package com.ceiba.ceibaapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.data.model.ItemUserDataState
import com.ceiba.ceibaapp.databinding.ActivityMainBinding
import com.ceiba.ceibaapp.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var rvUsers: RecyclerView
    private lateinit var listUsers: List<ItemUserDataState.User>
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.tittle_app)

        rvUsers = binding.recyclerViewUsers

        configSwipeRefresh()

        userViewModel.onCreate()
        userViewModel.usersModel.observe(this) {
            listUsers = it
            usersAdapter = UsersAdapter(it)
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(applicationContext)
            rvUsers.adapter = usersAdapter
        }

        userViewModel.isLoading.observe(this) {
            binding.swipe.isRefreshing = it
        }

        binding.textInputEdtUser.addTextChangedListener(userTextWatcher())
    }

    private fun userTextWatcher() = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            filter(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Without implementation
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Without implementation
        }
    }

    private fun filter(text: String) {
        val filteredNames = ArrayList<ItemUserDataState.User>()
        listUsers.filterTo(filteredNames) {
            it.name!!.lowercase().contains(text.lowercase())
        }
        usersAdapter.filterList(filteredNames)

        binding.recyclerViewUsers.visibility =
            if (filteredNames.isEmpty()) View.GONE else View.VISIBLE
        binding.txtEmpty.visibility = if (filteredNames.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun configSwipeRefresh() {
        binding.swipe.setColorSchemeColors(
            getColor(R.color.primaryColor),
            getColor(R.color.secondaryColor)
        )

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            userViewModel.getListUsers()
        }
    }
}