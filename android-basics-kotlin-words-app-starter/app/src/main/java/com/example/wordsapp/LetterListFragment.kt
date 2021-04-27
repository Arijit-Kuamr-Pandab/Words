package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {
    private var _binding: FragmentLetterListBinding? = null // To implement view binding in LetterListFragment,
                                                            // We first need to get a nullable reference to FragmentLetterListBinding

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLetterListBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView // Binding class already created a property for recyclerView, and you don't need to use findViewById()
        chooseLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null  // View no longer exists
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_layout,menu)

        val layoutManager = menu.findItem(R.id.switchMenu)
        setIcon(layoutManager)
    }

    private fun chooseLayout(){
        when(isLinearLayoutManager){
            true ->{
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
            false ->{
                recyclerView.layoutManager = GridLayoutManager(context,4)
            }
        }
    }
    private fun setIcon(menuItem: MenuItem) {
        if (menuItem == null) {
            return
        } else {
            menuItem.icon = if (isLinearLayoutManager) {
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_baseline_grid_on_24)
            } else {
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_baseline_list_24)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.switchMenu -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }
}