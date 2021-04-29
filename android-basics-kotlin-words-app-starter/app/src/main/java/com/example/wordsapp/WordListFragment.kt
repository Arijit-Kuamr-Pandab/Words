package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.ActivityMainBinding
import com.example.wordsapp.databinding.FragmentWordListBinding
import java.sql.Driver
import java.sql.DriverPropertyInfo

class WordListFragment : Fragment() {
    private var _binding: FragmentWordListBinding? = null

    private val binding get() = _binding!!

    private lateinit var letterId:String

    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = WordAdapter(letterId,requireContext())
        recyclerview.addItemDecoration(
                DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }



}