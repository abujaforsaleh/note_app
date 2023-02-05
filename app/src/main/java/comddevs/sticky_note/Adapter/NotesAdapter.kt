package comddevs.sticky_note.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import comddevs.sticky_note.Models.Note
import comddevs.sticky_note.R
import java.util.*
import kotlin.collections.ArrayList

class NotesAdapter(private val context : Context, val listener: NotesClickListener):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.note_tv.text =  currentNote.note
        holder.date.text =  currentNote.date
        holder.date.isSelected = true
        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(NotesList[holder.absoluteAdapterPosition])
        }
        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(NotesList[holder.absoluteAdapterPosition], holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return NotesList.size
    }
    fun updateList(newList: List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }
    fun filterList(search:String){
        NotesList.clear()
        for(item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase())==true ||
                item.note?.lowercase()?.contains(search.lowercase())==true){
                NotesList.add(item)
            }

        }
        notifyDataSetChanged()
    }


    fun randomColor(): Int{
        val list = ArrayList<Int>()
        list.add(R.color.note_color_1)
        list.add(R.color.note_color_2)
        list.add(R.color.note_color_3)
        list.add(R.color.note_color_4)
        list.add(R.color.note_color_5)
        list.add(R.color.note_color_6)
        list.add(R.color.note_color_7)
        list.add(R.color.note_color_8)
        list.add(R.color.note_color_9)
        list.add(R.color.note_color_10)
        //val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random().nextInt(list.size)
        return list[randomIndex]
    }
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date =  itemView.findViewById<TextView>(R.id.tv_date)
    }
    interface  NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }

}