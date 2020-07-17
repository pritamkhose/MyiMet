package com.raywenderlich.android.imet.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.raywenderlich.android.imet.data.db.PeopleDao
import com.raywenderlich.android.imet.data.db.PeopleDatabase
import com.raywenderlich.android.imet.data.model.People

class PeopleRepository(application: Application) {

  private val peopleDao: PeopleDao

  init {
    val peopleDatabase = PeopleDatabase.getInstance(application)
    peopleDao = peopleDatabase.peopleDao()
  }

  fun getAllPeople(): LiveData<List<People>> {
    return peopleDao.getAll()
  }

  fun insertPeople(people: People) {
    peopleDao.insert(people)
  }

  fun findPeople(id: Int): LiveData<People> {
    return peopleDao.find(id)
  }


  fun findPeople(name: String): LiveData<List<People>> {
    return peopleDao.findBy(name)
  }

  fun deletePeople(id: Int) {
    return peopleDao.deleteByID(id)
  }

}