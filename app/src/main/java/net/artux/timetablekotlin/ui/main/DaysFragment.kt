package net.artux.timetablekotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import net.artux.timetablekotlin.R
import net.artux.timetablekotlin.databinding.FragmentFirstBinding
import net.artux.timetablekotlin.ui.login.ViewModelsFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DaysFragment : Fragment() {

    lateinit var viewModel:MainViewModel
    lateinit var firstBinding:FragmentFirstBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        firstBinding = FragmentFirstBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return firstBinding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), ViewModelsFactory()).get(MainViewModel::class.java)

        val daysAdapter = DaysAdapter(emptyList(), viewModel.dateFormat)
        firstBinding.daysList.adapter = daysAdapter;

        view.findViewById<Button>(R.id.button_next).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewModel.listResult.observe(requireActivity(), Observer {
            daysAdapter.update(it)
        })

        daysAdapter.onItemClick = {
            val bundle = Bundle()
            bundle.putString("task_id", it?.iD)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        firstBinding.buttonNext.setOnClickListener { viewModel.nextWeek() }
        firstBinding.buttonPrevious.setOnClickListener { viewModel.previousWeek() }

        viewModel.getTimetable()
    }
}