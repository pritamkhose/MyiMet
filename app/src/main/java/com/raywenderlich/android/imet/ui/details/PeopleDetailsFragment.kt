package com.raywenderlich.android.imet.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.raywenderlich.android.imet.R
import com.raywenderlich.android.imet.data.model.People
import kotlinx.android.synthetic.main.people_details_fragment.*

class PeopleDetailsFragment : Fragment() {

    private lateinit var viewModel: PeopleDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PeopleDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.people_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find people with provided id
        val peopleId = arguments?.getInt(getString(R.string.people_id))
        peopleId?.let {
            viewModel.getPeopleDetails(peopleId).observe(viewLifecycleOwner, Observer { peopleDetails ->
                populatePeopleDetails(peopleDetails)
            })
        }
    }

    /**
     * Binds people info into views
     */
    private fun populatePeopleDetails(people: People?) {
        textViewName.text = people?.name
        textViewMet.text = people?.metAt
        buttonContact.text = people?.contact
        textViewEmail.text = people?.email
        textViewFacebook.text = people?.facebook
        textViewTwitter.text = people?.twitter
    }

}