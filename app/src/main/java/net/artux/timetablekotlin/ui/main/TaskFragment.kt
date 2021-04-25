package net.artux.timetablekotlin.ui.main

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat.fromHtml
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.artux.timetablekotlin.databinding.FragmentSecondBinding
import net.artux.timetablekotlin.ui.login.ViewModelsFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TaskFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    lateinit var secondBinding: FragmentSecondBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelsFactory()).get(MainViewModel::class.java)
        secondBinding = FragmentSecondBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return secondBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.taskData.observe(requireActivity(), Observer {
            println(it)

            secondBinding.view.text = fromHtml(it, Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE);
        })
        arguments?.getString("task_id", "")?.let { mainViewModel.getTask(it) }


    }
}