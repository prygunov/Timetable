package net.artux.timetablekotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import net.artux.timetablekotlin.databinding.FragmentListBinding
import net.artux.timetablekotlin.databinding.FragmentTaskBinding
import net.artux.timetablekotlin.dummy.DummyContent
import net.artux.timetablekotlin.ui.login.ViewModelsFactory
import net.artux.timetablekotlin.ui.main.MainViewModel

/**
 * A fragment representing a list of Items.
 */
class ListFragment : Fragment() {

    lateinit var listBinding: FragmentListBinding;

    lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelsFactory()).get(MainViewModel::class.java)
        listBinding = FragmentListBinding.inflate(inflater)
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val files = arguments?.getBoolean("files")
        if(files!=null)
        if (files){
            if (mainViewModel.getOccupation()?.links!=null) {
                requireActivity().title = "Файлы"
                val adapter = MyFileRecyclerViewAdapter(mainViewModel.getOccupation()?.links!!)
                listBinding.list.adapter = adapter
            }
        }else{
            requireActivity().title = "Решения"
            val adapter = mainViewModel.getOccupation()?.task?.decisons?.let {
                ResultsRecyclerViewAdapter(
                    it
                )
            }
            listBinding.list.adapter = adapter
        }

    }
}