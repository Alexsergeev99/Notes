import Note.addNote
import Note.commentId
import Note.createComment
import Note.deleteComment
import Note.deleteNote
import Note.editComment
import Note.editNote
import Note.getNotes
import Note.message
import Note.noteId
import Note.restoreComment
import Note.title

object Note {
    data class Notes(var noteId: Int = 0,
        var title: String? = null,
        var text: String? = null,
        var deletedComment: Boolean = false,
        var message: String? = null,
        var commentId: Int = 0
    )
    var title: String? = null
    var text: String? = null
    var noteId: Int = 0
    var deletedComment: Boolean = false
    var message: String? = null
    var commentId: Int = 0
        var notesTitleMap = mutableMapOf<Int, String>()
        var notesIdList = mutableListOf<Int>()
        var commentsMap = mutableMapOf<Int, String?>()
        var notesTextMap = mutableMapOf<Int, String>()
        var deleteComments = mutableMapOf<Int, String?>()
        var listm = mutableListOf<Notes>()

        fun addNote(title: String, text: String): Int {
//            notesIdList.add(noteId++)
//            notesTitleMap.put(noteId, title)
//            notesTextMap.put(noteId, text)
            this.title = title
            this.text = text
            noteId = listm.lastIndex + 1
            listm.add(Notes(noteId++, title = title, text = text))
            return noteId
        }

        fun createComment(noteId: Int, message: String): Int {
                commentsMap.put(noteId, message)
                this.message = message
                text = listm[noteId - 1].text
                title = listm[noteId-1].title
//                commentId++
                listm.set(
                    noteId - 1, Notes(
                        noteId = noteId,
                        text = listm[noteId - 1].text,
                        title = listm[noteId-1].title,
                        commentId = commentId + 1,
                        message = message
                    )
                )

            return noteId
        }

        fun deleteNote(noteId: Int, title: String): Int {
//                notesIdList.remove(noteId)
//                notesTitleMap.remove(noteId)
//                notesTextMap.remove(noteId)
            deleteComment(noteId)
            listm.removeAt(noteId - 1)
            return 1
        }

        fun deleteComment(commentId: Int): Int {
//            deleteComments.put(commentId, commentsMap.get(commentId))
//            commentsMap.remove(commentId, message)
//        this.message = null
            if(listm[commentId - 1].message != null) {
                deletedComment = true
                message = null
                listm.set(
                    commentId - 1, Notes(
                        noteId = noteId,
                        text = listm[commentId - 1].text,
                        title = listm[commentId - 1].title,
                        deletedComment = deletedComment,
                        message = null,
                        commentId = commentId
                    )
                )
            }
            return 1
        }

        fun editNote(noteId: Int, title: String, text: String): Int {
            notesTextMap.set(noteId, text)
            notesTitleMap.set(noteId, title)
            this.title = title
            this.text = text
            listm.set(noteId - 1, Notes(title = title, text = text))
            return noteId
        }

        fun editComment(noteId: Int, message: String): Int {
            if (deletedComment == false) {
                commentsMap.set(noteId, message)
                listm.set(commentId - 1, Notes(
                    noteId = noteId,
                    text = listm[noteId - 1].text,
                    title = listm[noteId-1].title,
                    commentId = commentId,
                    message = message))
            }
            return 1
        }

        fun restoreComment(commentId: Int): Int {
            if (deletedComment == true) {
                commentsMap.put(commentId, commentsMap.get(commentId))
                deleteComments.remove(commentId)
//        this.message = commentsMap.get(commentId)
                deletedComment = false
                listm.set(commentId - 1,
                    Notes(noteId = noteId,
                        text = listm[commentId - 1].text,
                        title = listm[commentId-1].title,
                        commentId = commentId,
                        message = message))
            }
            return 1
        }
    fun getNotes(): MutableList<Note.Notes>  {
        return listm
    }
    fun getById(noteId: Int): Note.Notes {
        return listm[noteId - 1]
    }
    fun getCommentsById(noteId: Int): String?{
        return listm[noteId - 1].message
    }

    fun clear() {
            listm = mutableListOf()
    }
}

fun main() {

//    val list = mutableListOf<Note.Notes>()

//    val note = Note.Notes()
//    list.add(note)
    addNote("hello", "world")
    createComment(1, "wow")
    addNote("yui", "yui")
    createComment(2, "nope")
    deleteComment(1)
//    println(message)
//    println(getNotes())
    restoreComment(1)
//    println(getCommentsById(1))
    deleteNote(1, "hello")
//    println(getNotes())
//    editComment(1, "note")
    println(getNotes())
}