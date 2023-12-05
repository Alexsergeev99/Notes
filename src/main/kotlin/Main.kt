import Note.addNote
import Note.commentId
import Note.createComment
import Note.deleteComment
import Note.deleteNote
import Note.editComment
import Note.editNote
import Note.getNotes
import Note.noteId
import Note.restoreComment

data class Notes(
    var noteId: Int = 0,
    var title: String? = null,
    var text: String? = null,
    var comments: MutableMap<Int/*commentId*/, Comment> = mutableMapOf()
)

data class Comment(
    val commentId: Int = 0,
    val noteId: Int = 0, //id заметки, к которой оставлен комментарий
    var message: String? = null, //Текст комментария
    var deletedComment: Boolean = false //Удалён ли комментарий
)

object Note {
    var mapNotes = mutableMapOf<Int/*noteId*/, Notes>()
    var noteId: Int = 0 // Счётчик для создания id заметок
    var commentId: Int = 0 // Счётчик для создания id комментариев
    fun clear() { // Сбрасываем поля в исходные значения
        mapNotes = mutableMapOf()
        noteId = 0
        commentId = 0
    }

    fun addNote(title: String?, text: String?): Int {

        mapNotes[++noteId] = Notes(noteId = noteId, title = title, text = text)
        return noteId
    }

    fun createComment(noteId: Int, message: String): Int {

        val note = mapNotes[noteId]
            ?: return -1 // Если удалить не удалось - вернёт null, с помощью элвис оператора возвращаем -1
        note.comments[++commentId] = Comment(commentId, noteId, message) //Создаём новый комментарий во внутренней мапе
        return commentId
    }

    fun deleteNote(noteId: Int): Boolean {
        mapNotes.remove(noteId) ?: return false
        return true
    }

    fun deleteComment(noteId: Int, commentId: Int): Boolean {
        val note = mapNotes[noteId] ?: return false
        val comment = note.comments[commentId] ?: return false
        comment.deletedComment = true
        return true
    }

    fun editNote(noteId: Int, title: String, text: String): Boolean {

        val note = mapNotes[noteId] ?: return false
        mapNotes[noteId] = Notes(noteId = noteId, title = title, text = text, note.comments)

        return true
    }

    fun editComment(noteId: Int, commentId: Int, message: String): Boolean {
        val note = mapNotes[noteId] ?: return false
        val comment = note.comments[commentId] ?: return false
        comment.message = message
        return true
    }

    fun restoreComment(noteId: Int, commentId: Int): Boolean {
        val note = mapNotes[noteId] ?: return false
        val comment = note.comments[commentId] ?: return false
        comment.deletedComment = false
        return true
    }

    fun getNotes(): MutableMap<Int, Notes> {
        return mapNotes
    }

    fun getById(noteId: Int): Notes {
        return mapNotes.getValue(noteId)
    }

    fun getCommentsById(noteId: Int): Comment? {
        val note = mapNotes[noteId] ?: return null
        return note.comments[noteId]
    }

}

fun main() {

    addNote("hello", "world")
    createComment(1, "wow")
    addNote("yui", "yui")
    createComment(2, "nope")
    editNote(1, "bye", "bye")
    deleteComment(1, 1)
//    println(message)
//    println(getNotes())
    restoreComment(1, 1)
//    println(getCommentsById(1))
//    deleteNote(1)
//    println(getNotes())
//    editComment(1, "note")
    println(getNotes())
}