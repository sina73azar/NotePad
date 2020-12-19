package com.sina.notepadfinal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.sina.notepadfinal.datamodel.Note
import com.sina.notepadfinal.R
import com.sina.notepadfinal.databinding.FragmentEditNoteBinding
import com.sina.notepadfinal.utils.hideKeyboard
import com.sina.notepadfinal.utils.toEditable
import java.util.*


class EditNoteFragment : Fragment() {

    lateinit var binding: FragmentEditNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentEditNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var inEditMode=false
        val position=EditNoteFragmentArgs.fromBundle(requireArguments()).position
        if (position != -1) {
            val item=db.listOfNotes[position]

            binding.etGetTitle.text=item.title.toEditable()
            binding.etValueNote.text=item.noteValue.toEditable()
            inEditMode=true
            binding.btnSaveNote.text=getString(R.string.text_btn_edit)
        }

        binding.btnSaveNote.setOnClickListener {
            val title=binding.etGetTitle.text.toString()
            val noteValue=binding.etValueNote.text.toString()
            if (title.isNotEmpty()) {
                val date=Date().time
                val myNote = Note(title, noteValue,date)
                if (inEditMode) {
                    db.listOfNotes[position]=myNote
                }else{
                    db.addToList(myNote)
                }
                //close keyboard
                hideKeyboard()
                //navigate back to list fragment
                findNavController().navigate(R.id.action_editNoteFragment_to_listNoteFragment)
            } else {
                Toast.makeText(activity, "enter title", Toast.LENGTH_SHORT).show()
            }
        }
    }


}