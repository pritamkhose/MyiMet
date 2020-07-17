package com.raywenderlich.android.imet.ui.add

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.android.imet.R

class AddPeopleFragment : Fragment() {

    companion object {
        fun newInstance() =
            AddPeopleFragment()
    }

    private lateinit var viewModel: AddPeopleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_people_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddPeopleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}