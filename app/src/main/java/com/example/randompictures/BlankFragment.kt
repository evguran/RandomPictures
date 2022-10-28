package com.example.randompictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.randompictures.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    lateinit var binding: FragmentBlankBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvFragText.setOnClickListener {
            dataModel.messageForActivity.value = "Hello from fragment"
        }
        dataModel.messageForFragment.observe(activity as LifecycleOwner, {
            binding.tvFragText.text = it
        })

    }
    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}