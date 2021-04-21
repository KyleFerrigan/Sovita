package com.kernelpanic.sovita.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kernelpanic.sovita.R


class ChatFragment : Fragment() {

    private lateinit var chatViewModel: ChatViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        chatViewModel =
                ViewModelProvider(this).get(ChatViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        val textView2: TextView = root.findViewById(R.id.text_notifications2)
        val textView3: TextView = root.findViewById(R.id.text_notifications3)
        val textView4: TextView = root.findViewById(R.id.text_notifications4)
        chatViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
            textView2.text = it
            textView3.text = it
            textView4.text = it
        })
        return root
    }
}