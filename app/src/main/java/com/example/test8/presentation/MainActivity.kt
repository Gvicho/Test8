package com.example.test8.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.test8.R
import com.example.test8.databinding.ActivityMainBinding
import com.example.test8.presentation.extensions.showSnackBar
import com.example.test8.presentation.model.Option
import com.example.test8.presentation.model.Type
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),CallBackListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Handle navigation item selected event
        navView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Handle back button event
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        val headerView = navView.getHeaderView(0)
        val recyclerView = headerView.findViewById<RecyclerView>(R.id.nav_header_recyclerview)
        recyclerView.adapter = NavigationOptionsRecyclerAdapter(getHeaderItems(),this)

    }


    private fun getHeaderItems(): List<Option> {
        return listOf(
            Option(1,R.drawable.settings_svgrepo_com,"vai 1",null,Type.NORMAL),
            Option(2,R.drawable.bell_svgrepo_com,"notifications",null,Type.NORMAL),
            Option(3,R.drawable.ic_baseline_add_24,"add",null,Type.NORMAL),
            Option(4,R.drawable.calendar_days_svgrepo_com,"calendar",null,Type.NORMAL),
            Option(5,null,"",null,Type.Switch)
        )
    }

    override fun onOptionClicked(optionId: Int) {
        binding.root.showSnackBar("no navigation for this option for now")
    }
}