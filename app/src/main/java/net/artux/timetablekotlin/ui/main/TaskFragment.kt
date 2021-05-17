package net.artux.timetablekotlin.ui.main

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import net.artux.timetablekotlin.R
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
            if (isAdded)
                requireActivity().title = it.title
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
            if(it.links!=null)
            if (it.links!!.size!=0){
                taskBinding.filesButton.visibility = View.VISIBLE
                taskBinding.filesButton.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putBoolean("files", true)
                    findNavController().navigate(R.id.action_SecondFragment_to_listFragment_files, bundle)
                }
            }
            val task = it.task
            if (task != null){
                taskBinding.taskContainer.visibility = View.VISIBLE
                taskBinding.resultsButton.visibility = View.VISIBLE
                taskBinding.resultsButton.setOnClickListener {
                    findNavController().navigate(R.id.action_SecondFragment_to_listFragment_results)
                }
                taskBinding.taskView.text = Html.fromHtml(task.description, Html.FROM_HTML_MODE_LEGACY)
            }
        })
        arguments?.getString("task_id", "")?.let {
            mainViewModel.getTask(it)
        }
        taskBinding.chatButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("occupation_id", mainViewModel.getOccupation()?.id)
            bundle.putString("student_id", mainViewModel.getOccupation()?.studentId)
            findNavController().navigate(R.id.action_SecondFragment_to_chatFragment, bundle)
        }
    }
}