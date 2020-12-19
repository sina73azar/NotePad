package com.sina.notepadfinal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sina.notepadfinal.utils.MyRecyclerAdapter
import com.sina.notepadfinal.R
import com.sina.notepadfinal.databinding.FragmentListNoteBinding
import com.sina.notepadfinal.db.MyDb


val db=MyDb()
class ListNoteFragment : Fragment() {

    lateinit var binding: FragmentListNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentListNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check if list is empty

        setupRecyclerView()

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(ListNoteFragmentDirections.actionListNoteFragmentToEditNoteFragment(-1))
        }
    }

    private fun setupRecyclerView() {

        if (db.listOfNotes.isNotEmpty()) {
            //lambda magic is about to happen...
            val myClickListener:(position:Int,view:View)->Unit={ position: Int, view: View ->
                when (view.id) {
                    R.id.img_delete -> {
                        db.listOfNotes.removeAt(position)
                        activity?.recreate()

                    }
                    R.id.img_edit -> {
                        goToEditFragment(position)
                    }
                    R.id.tv_show_note_value,R.id.tv_title ->{
                        goToShowNoteReadOnlyFragment(position,view)
                    }

                }
            }

            val myLongClickListener:(position:Int,view:View)->Boolean={ position: Int, view: View ->
                when (view.id) {
                    R.id.img_delete -> {
                        Toast.makeText(activity, "LongClicked view is delete}", Toast.LENGTH_SHORT).show()
                    }
                    R.id.img_edit -> {
                        Toast.makeText(activity, "LongClicked view is edit", Toast.LENGTH_SHORT).show()
                    }
                    R.id.tv_show_note_value,R.id.tv_title ->{
                        Toast.makeText(activity, "LongClicked view is note", Toast.LENGTH_SHORT).show()

                    }

                }
                true
            }
            binding.tvShowEmptyList.visibility = View.GONE
            binding.rvNotes.visibility = View.VISIBLE
            binding.rvNotes.adapter = MyRecyclerAdapter(db.listOfNotes,myClickListener,myLongClickListener)

        }

    }
    private fun goToShowNoteReadOnlyFragment(position:Int,view: View) {
        val navDirections=ListNoteFragmentDirections.actionListNoteFragmentToReadOnlyNoteFragment(position)
        findNavController().navigate(navDirections)
    }

    private fun goToEditFragment(position: Int) {

        val navDirections =
            ListNoteFragmentDirections.actionListNoteFragmentToEditNoteFragment(position)
        findNavController().navigate(navDirections)

    }


}