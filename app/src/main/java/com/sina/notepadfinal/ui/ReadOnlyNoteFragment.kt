
package com.sina.notepadfinal.ui

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


import com.sina.notepadfinal.databinding.FragmentReadOnlyNoteBinding
import com.sina.notepadfinal.utils.reformat


class ReadOnlyNoteFragment : Fragment() {

    lateinit var binding: FragmentReadOnlyNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentReadOnlyNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val curNote= ReadOnlyNoteFragmentArgs.fromBundle(requireArguments()).curNote1
        curNote.let {
            binding.tvShowNoteValueFrgReadOnly.text=it.noteValue
            binding.tvDateFrgReadOnly.text=it.date.reformat()
            binding.tvTitleFrgReadOnly.text=it.title
        }
    }



}