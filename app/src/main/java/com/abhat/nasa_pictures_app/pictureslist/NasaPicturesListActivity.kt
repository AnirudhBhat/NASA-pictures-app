package com.abhat.nasa_pictures_app.pictureslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.abhat.nasa_pictures_app.ConnectionMonitorLiveData
import com.abhat.nasa_pictures_app.R

class NasaPicturesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNetworkErrorToastIfNotAvailable()
        observeConnectionLiveData()
        if (savedInstanceState == null) {
            createFragment(NasaPicturesListFragment.newInstance())
        }
    }

    private fun showNetworkErrorToastIfNotAvailable() {
        if (ConnectionMonitorLiveData.isNetworkAvaiable().not()) {
            Toast.makeText(this, getString(R.string.error_network_offline), Toast.LENGTH_LONG).show()
        }
    }

    private fun observeConnectionLiveData() {
        ConnectionMonitorLiveData.observe(this, Observer { networkStatus ->
            if (networkStatus.not()) {
                Toast.makeText(this, getString(R.string.error_network_offline), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun createFragment(fragment: Fragment) {
        if (!isFinishing) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, fragment, TAG)
            ft.commitAllowingStateLoss()
        }
    }

    companion object {
        const val TAG = "nasa_pictures_list"
    }
}
