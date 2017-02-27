package com.exallium.todoapp.notedetail

import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import com.exallium.todoapp.R
import com.exallium.todoapp.app.TodoApp
import com.exallium.todoapp.entities.Note
import com.exallium.todoapp.mvp.BaseViewImpl
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Conductor Controller displaying a single note
 */
class NoteDetailViewImpl(val bundle: Bundle) : BaseViewImpl<NoteDetailView, NoteDetailPresenter, NoteDetailViewImpl,
        NoteDetailComponent>(bundle), NoteDetailView {
    @BindView(R.id.note_title)
    lateinit var noteTitleTextView: TextView

    @BindView(R.id.note_body)
    lateinit var noteBodyTextView: TextView

    @BindView(R.id.note_date_created)
    lateinit var noteDateCreated: TextView

    @BindView(R.id.note_date_updated)
    lateinit var noteDateUpdated: TextView

    private var lastNoteId: String? = null

    override fun getComponent(): NoteDetailComponent = DaggerNoteDetailComponent.builder()
            .todoAppComponent(TodoApp.component)
            .noteDetailModule(NoteDetailModule(this))
            .build()

    override fun getLayoutId(): Int = R.layout.note_detail_view

    override fun setNoteData(note: Note) {
        lastNoteId = note.id
        noteTitleTextView.text = note.title
        noteBodyTextView.text = note.body
        setDate(noteDateCreated, note.created)
        setDate(noteDateUpdated, note.updated)
    }

    override fun isCached(noteId: String): Boolean {
        if (lastNoteId != null && lastNoteId.equals(noteId)) { true
            Timber.d("Note already looked up")
            return true
        }
        return false
    }

    override fun retrieveBundle(): Bundle {
        return bundle
    }

    private fun setDate(field: TextView, valueInMillis: Long) {
        if (valueInMillis == 0L) {
            field.text = SimpleDateFormat().format(Date(valueInMillis))
        }
        field.text = SimpleDateFormat().format(Date(valueInMillis))
    }
}
