package com.raywenderlich.android.imet.ui.details

import android.app.Application
import androidx.lifecycle.*
import com.raywenderlich.android.imet.IMetApp
import com.raywenderlich.android.imet.data.model.People

class PeopleDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val peopleRepository = getApplication<IMetApp>().getPeopleRepository()
    private val peopleId = MutableLiveData<Int>()

    // Maps people id to people details
    fun getPeopleDetails(id: Int): LiveData<People> {
        peopleId.value = id
        val peopleDetails = Transformations.switchMap<Int, People>(peopleId) { id ->
            peopleRepository.findPeople(id)
        }
        return peopleDetails
    }

    fun deletePeople(peopleID: Int) {
        peopleRepository.deletePeople(peopleID)
    }

}