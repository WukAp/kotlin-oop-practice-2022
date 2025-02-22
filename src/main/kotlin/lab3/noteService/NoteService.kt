package lab3.noteService

import lab3.note.Note
import lab3.note.Note.*
import java.net.URL
import java.time.LocalDateTime
import java.util.logging.Logger

val LOG: Logger = Logger.getLogger(NoteService()::class.java.name)

class NoteService() : NoteServiceInterface {
    override val noteList
        get() = _noteMutableList.toList()

    private val _noteMutableList: MutableList<Note> = mutableListOf<Note>()


    override fun add(note: Note) {
        _noteMutableList.add(note)
    }

    override fun getAllNotes(): List<Note> = noteList


    override fun getAllTextNotes(): List<TextNote> = _noteMutableList.filterIsInstance<TextNote>()
    override fun getAllTasks(): List<Task> = _noteMutableList.filterIsInstance<Task>()
    override fun getAllLinks(): List<Link> = _noteMutableList.filterIsInstance<Link>()


    override fun createTextNote(title: String, content: String): TextNote {
        LOG.info("new text note has been added")
        return TextNote(title, content, LocalDateTime.now())
    }

    override fun createTask(title: String, task: String, deadline: LocalDateTime): Task {
        LOG.info("new task note has been added")
        return Task(title, task, deadline, LocalDateTime.now())
    }

    override fun createLink(title: String, content: String, url: URL): Link {
        LOG.info("new link note has been added")
        return Link(title, content, LocalDateTime.now(), url)
    }


    override fun removeNote(note: Note) {
        if (_noteMutableList.remove(note))
            LOG.info("note has been removed")
    }

    override fun findByTitle(title: String): List<Note> = _noteMutableList.filter { it.title == title }
    override fun findByType(type: Class<Any>): List<Note> = _noteMutableList.filter { it.javaClass == type }

    override fun getSortedByTitle(): List<Note> = _noteMutableList.toMutableList().sortedBy { it.title }
    override fun getSortedByDate(): List<Note> = _noteMutableList.toMutableList().sortedBy { it.date }
}