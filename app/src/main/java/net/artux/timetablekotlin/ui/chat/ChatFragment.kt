package net.artux.timetablekotlin.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.artux.timetablekotlin.databinding.FragmentChatBinding
import net.artux.timetablekotlin.ui.login.ViewModelsFactory
import net.artux.timetablekotlin.ui.main.MainViewModel

/**
 * A fragment representing a list of Items.
 */
class ChatFragment : Fragment() {

    private var columnCount = 1
    lateinit var chatBinding:FragmentChatBinding
    lateinit var chatViewModel:ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chatViewModel = ViewModelProvider(requireActivity(), ViewModelsFactory()).get(ChatViewModel::class.java)
        chatBinding = FragmentChatBinding.inflate(inflater);
        return chatBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = MessageRecyclerViewAdapter(emptyList())
        chatBinding.recycleView.adapter = adapter

        chatViewModel.listResult.observe(requireActivity(), Observer {
            adapter.updateMessages(it)
        })
        arguments?.getString("task_id")?.let {
            chatViewModel.getMessages(it)
        }



    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}