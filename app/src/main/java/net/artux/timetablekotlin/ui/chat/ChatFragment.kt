package net.artux.timetablekotlin.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.artux.timetablekotlin.databinding.FragmentChatBinding
import net.artux.timetablekotlin.ui.login.ViewModelsFactory


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
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        chatBinding.recyclerView.layoutManager = layoutManager
        chatBinding.recyclerView.adapter = adapter



        chatViewModel.listResult.observe(requireActivity(), Observer {
            adapter.updateMessages(it)
            if (adapter.itemCount!=0)
                chatBinding.recyclerView.smoothScrollToPosition(adapter.itemCount - 1);
        })
        chatViewModel.messageSent.observe(requireActivity(), Observer {
            if (it){
                chatBinding.inputText.setText("")
                update()
            }else
                Toast
                    .makeText(requireContext(), "Не удалось отправить", Toast.LENGTH_SHORT)
                    .show();
        })

        chatBinding.updateButton.setOnClickListener {
            update()
        }
        chatBinding.sendButton.setOnClickListener {
            arguments?.let {
                chatViewModel.sendMessage(it.getString("occupation_id").toString(),
                    it.getString("student_id").toString(),
                    chatBinding.inputText.text.toString())
            }

        }



        update()
    }

    private fun update(){
        arguments?.getString("occupation_id")?.let {
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