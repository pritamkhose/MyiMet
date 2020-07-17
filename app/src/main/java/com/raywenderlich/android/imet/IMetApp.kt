package com.raywenderlich.android.imet

import android.app.Application
import com.raywenderlich.android.imet.data.PeopleRepository

class IMetApp : Application() {

  /**
   * Provides centralised Repository throughout the app
   */
  fun getPeopleRepository() = PeopleRepository(this)

}
