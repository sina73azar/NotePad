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
        val curPos= ReadOnlyNoteFragmentArgs.fromBundle(requireArguments()).curPos
        curPos.let {

            val item = db.listOfNotes[it]
            binding.tvShowNoteValueFrgReadOnly.text=item.noteValue
            binding.tvDateFrgReadOnly.text=item.date.reformat()
            binding.tvTitleFrgReadOnly.text=item.title
        }
    }



}