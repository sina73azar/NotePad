package com.sina.notepadfinal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sina.notepadfinal.R
import com.sina.notepadfinal.databinding.FragmentListNoteBinding
import com.sina.notepadfinal.datamodel.Note
import com.sina.notepadfinal.db.MyRoomDataBase
import com.sina.notepadfinal.db.NoteDao
import com.sina.notepadfinal.utils.MyRecyclerAdapter


class ListNoteFragment : Fragment() {

    lateinit var binding: FragmentListNoteBinding
    private lateinit var dao: NoteDao
    lateinit var adapter: MyRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = MyRoomDataBase.getDatabase(requireActivity().applicationContext).getNoteDao()
        //check if list is empty

        setupRecyclerView()
        handleClicks()

    }

    private fun handleClicks() {
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(
                ListNoteFragmentDirections.actionListNoteFragmentToEditNoteFragment(
                    Note("", "", 1)
                )
            )
        }
    }

    private fun setupRecyclerView() {
        val noteList = dao.getNoteList()
        adapter=MyRecyclerAdapter(
            noteList,
            clickListener,
            myLongClickListener
        )
        if (noteList.count() != 0) {
            //we are going to show the list
            binding.rvNotes.visibility = View.VISIBLE
            binding.tvShowEmptyList.visibility = View.GONE
            //set adapter to rv
            binding.rvNotes.adapter = adapter
        } else {
            //list is empty so we hide it and show a text only
            binding.tvShowEmptyList.visibility = View.VISIBLE
            binding.rvNotes.visibility = View.GONE
        }

    }


    private val clickListener: (Note, Int, View) -> (Unit) = { note: Note, pos: Int, view: View ->
        when (view.id) {
            R.id.img_delete -> {
                dao.deleteNote(note)
               binding.rvNotes.removeViewAt(pos)
                adapter.notifyItemRemoved(pos)
//                adapter.notifyItemRangeChanged(pos, dao.getCount());

                adapter.listNotes=dao.getNoteList()

            }

            R.id.img_edit -> {
                goToEditFragment(note)
            }
            R.id.tv_show_note_value, R.id.tv_title -> {
                goToShowNoteReadOnlyFragment(note)
            }
            else -> {
                Toast.makeText(requireContext(), note.title, Toast.LENGTH_SHORT).show()
            }

        }
    }
    private val myLongClickListener: (Note, View) -> Boolean = { note: Note, view: View ->
        when (view.id) {
            R.id.img_delete -> {
                Toast.makeText(
                    activity,
                    "LongClicked view is delete}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            R.id.img_edit -> {
                Toast.makeText(activity, "LongClicked view is edit", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.tv_show_note_value, R.id.tv_title -> {
                Toast.makeText(activity, "LongClicked view is note", Toast.LENGTH_SHORT)
                    .show()

            }

        }
        true
    }


    private fun goToShowNoteReadOnlyFragment(note: Note) {
        val navDirections =
            ListNoteFragmentDirections.actionListNoteFragmentToReadOnlyNoteFragment(note)
        findNavController().navigate(navDirections)
    }

    private fun goToEditFragment(note: Note) {

        val navDirections =
            ListNoteFragmentDirections.actionListNoteFragmentToEditNoteFragment(note)
        findNavController().navigate(navDirections)

    }

}







