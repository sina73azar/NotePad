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
import com.sina.notepadfinal.db.MyRoomDataBase
import com.sina.notepadfinal.db.NoteDao
import com.sina.notepadfinal.utils.hideKeyboard
import com.sina.notepadfinal.utils.toEditable
import java.util.*


class EditNoteFragment : Fragment() {
    lateinit var dao:NoteDao
    lateinit var binding: FragmentEditNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao= MyRoomDataBase.getDatabase(requireActivity().applicationContext).getNoteDao()
        val curNote = EditNoteFragmentArgs.fromBundle(requireArguments()).curNote
        //decide we are in add or edit mode
        val isInAddMode = curNote.date == 1L
        if (isInAddMode) {
            //we are in add mode

        } else {
            //we are in edit mode
            binding.etGetTitle.text = curNote.title.toEditable()
            binding.etValueNote.text = curNote.noteValue.toEditable()
            binding.btnSaveNote.text = getString(R.string.text_btn_edit)
        }


        binding.btnSaveNote.setOnClickListener {
            val title = binding.etGetTitle.text.toString()
            val noteValue = binding.etValueNote.text.toString()
            if (title.isNotEmpty()) {
                val date = Date().time
                if (isInAddMode) {
                    dao.insertNote(Note(title, noteValue, date))
                } else {
                    dao.updateNote(Note(title, noteValue, date, curNote.id))
                }
                //close keyboard
                hideKeyboard()
                //navigate back to list fragment
                findNavController().navigate(R.id.action_editNoteFragment_to_listNoteFragment)
            } else Toast.makeText(activity, "enter title", Toast.LENGTH_SHORT).show()

        }
    }

}