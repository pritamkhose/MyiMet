package com.raywenderlich.android.imet.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.raywenderlich.android.imet.R
import com.raywenderlich.android.imet.data.model.People
import kotlinx.android.synthetic.main.peoples_list_fragment.*

class PeoplesListFragment : Fragment(), PeoplesListAdapter.OnItemClickListener,
    SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    companion object {
        fun newInstance() =
            PeoplesListFragment()
    }

    private lateinit var searchView: SearchView
    private lateinit var viewModel: PeoplesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(PeoplesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.peoples_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_peoples_list, menu)

        // Initialize Search View
        searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Start observing people list
        viewModel.getPeopleList().observe(viewLifecycleOwner, Observer<List<People>> { peoples ->
            peoples?.let {
                populatePeopleList(peoples)
            }
        })

        // Navigate to add people
        addFab.setOnClickListener {
//            view.findNavController().navigate(R.id.action_peoplesListFragment_to_addPeopleFragment)
        }
    }

    /**
     * Callback for searchView text change
     */
    override fun onQueryTextChange(newText: String?) = true

    /**
     * Callback for searchView query submit
     */
    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchPeople(query!!)
        return true
    }

    /**
     * Callback for searchView close
     */
    override fun onClose(): Boolean {
        viewModel.getAllPeople()
        searchView.onActionViewCollapsed()
        return true
    }

    /**
     * Populates peopleRecyclerView with all people info
     */
    private fun populatePeopleList(peopleList: List<People>) {
        peopleRecyclerView.adapter = PeoplesListAdapter(peopleList, this)
    }

    /**
     * Navigates to people details on item click
     */
    override fun onItemClick(people: People, itemView: View) {
        val peopleBundle = Bundle().apply {
            putInt(getString(R.string.people_id), people.id)
        }
//        view?.findNavController()
//            ?.navigate(R.id.action_peoplesListFragment_to_peopleDetailsFragment, peopleBundle)
    }



}