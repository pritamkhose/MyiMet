package com.raywenderlich.android.imet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raywenderlich.android.imet.ui.main.list.PeoplesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PeoplesListFragment.newInstance())
                    .commitNow()
        }

    }

}