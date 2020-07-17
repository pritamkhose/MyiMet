package com.raywenderlich.android.imet.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.raywenderlich.android.imet.R
import com.raywenderlich.android.imet.data.model.People
import kotlinx.android.synthetic.main.people_details_fragment.*
import kotlin.properties.Delegates

class PeopleDetailsFragment : Fragment() {

    private lateinit var viewModel: PeopleDetailsViewModel
    private var peopleId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        peopleId = arguments?.getInt(getString(R.string.people_id))!!
        peopleId?.let {
            viewModel.getPeopleDetails(peopleId)
                .observe(viewLifecycleOwner, Observer { peopleDetails ->
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_details_people, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.menu_delete -> {
                deltePeople()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deltePeople() {
        viewModel.deletePeople(peopleId)
        Navigation.findNavController(requireView()).navigateUp()
    }

}