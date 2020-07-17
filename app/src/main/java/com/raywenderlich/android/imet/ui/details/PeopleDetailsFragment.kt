package com.raywenderlich.android.imet.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.imet.R

class PeopleDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PeopleDetailsFragment()
    }

    private lateinit var viewModel: PeopleDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.people_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PeopleDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}