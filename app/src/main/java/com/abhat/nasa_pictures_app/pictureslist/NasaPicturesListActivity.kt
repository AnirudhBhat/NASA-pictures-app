package com.abhat.nasa_pictures_app.pictureslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.abhat.nasa_pictures_app.R

class NasaPicturesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            createFragment(NasaPicturesListFragment.newInstance())
        }
    }

    private fun createFragment(fragment: Fragment) {
        if (!isFinishing) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, fragment, "nasa_pictures_list")
            ft.commitAllowingStateLoss()
        }
    }
}
