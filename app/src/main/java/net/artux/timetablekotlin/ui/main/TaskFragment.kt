package net.artux.timetablekotlin.ui.main

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.artux.timetablekotlin.databinding.FragmentTaskBinding
import net.artux.timetablekotlin.ui.login.ViewModelsFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TaskFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    lateinit var taskBinding: FragmentTaskBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelsFactory()).get(MainViewModel::class.java)
        taskBinding = FragmentTaskBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return taskBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.occupationData.observe(requireActivity(), Observer {
            taskBinding.teacherView.text = it.teacher
            taskBinding.subjectView.text = it.title
            taskBinding.typeView.text = it.type
            taskBinding.dateView.text = it.date
            taskBinding.timeView.text = it.time
            taskBinding.placeView.text = it.place
            taskBinding.groupsView.text = it.groups.toString()

            if (it.description != null){
                taskBinding.descriptionContainer.visibility = View.VISIBLE
                taskBinding.descriptionView.text = Html.fromHtml(it.description, Html.FROM_HTML_MODE_LEGACY)
            }
            val task = it.task
            if (task != null){
                taskBinding.taskContainer.visibility = View.VISIBLE
                taskBinding.taskView.text = Html.fromHtml(task.description, Html.FROM_HTML_MODE_LEGACY)
            }


        })
        arguments?.getString("task_id", "")?.let {
            mainViewModel.getTask(it)
        }
    }
}