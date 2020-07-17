package com.raywenderlich.android.imet.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.raywenderlich.android.imet.IMetApp
import com.raywenderlich.android.imet.data.model.People

class PeoplesListViewModel(application: Application) : AndroidViewModel(application) {
    private val peopleRepository = getApplication<IMetApp>().getPeopleRepository()
    private val peopleList = MediatorLiveData<List<People>>()

    init {
        getAllPeople()
    }

    // 1
    fun getPeopleList(): LiveData<List<People>> {
        return peopleList
    }

    // 2
    fun getAllPeople() {
        peopleList.addSource(peopleRepository.getAllPeople()) { peoples ->
            peopleList.postValue(peoples)
        }
    }

    fun searchPeople(name: String) {
        peopleList.addSource(peopleRepository.findPeople(name)) { peoples ->
            peopleList.postValue(peoples)
        }
    }
}