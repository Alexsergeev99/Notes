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

//data class Notes(
//    var noteId: Int = 0,
//    var title: String? = null,
//    var text: String? = null,
//    var comments: MutableMap<Int/*commentId*/, Comment> = mutableMapOf()
//)
//data class Comment(
//    val commentId: Int = 0,
//    val noteId: Int = 0, //id заметки, к которой оставлен комментарий
//    var text: String? = null, //Текст комментария
//    var isDeleted: Boolean = false //Удалён ли комментарий
//)
object Note {
    private var mapNotes = mutableMapOf<Int/*noteId*/, Notes>()
//    private var commentsMap = mutableMapOf<Int/*noteId*/, Comment>() //Если не хранить в поле класса Note
    private var idCounter: Int = 0
//    fun clear() {
//        mapNotes = mutableMapOf()
////        commentsMap = mutableMapOf()
//        noteId = 0
//    }
    data class Notes(var noteId: Int = 0,
        var title: String? = null,
        var text: String? = null,
        var deletedComment: Boolean = false,
        var message: String? = null,
        var commentId: Int = 0,
//        var comments: MutableMap<Int/*commentId*/, Comment> = mutableMapOf()
)
//data class Comment(
//    val commentId: Int = 0,
//    val noteId: Int = 0, //id заметки, к которой оставлен комментарий
//    var message: String? = null, //Текст комментария
//    var deletedComment: Boolean = false //Удалён ли комментарий
//)

    var title: String? = null
    var text: String? = null
    var noteId: Int = 0
    var deletedComment: Boolean = false
    var message: String? = null
    var commentId: Int = 0
//    var comments: MutableMap<Int/*commentId*/, Comment> = mutableMapOf()

    var notesTitleMap = mutableMapOf<Int, String>()
        var notesIdList = mutableListOf<Int>()
        //var commentsMap = mutableMapOf<Int, String?>()
        var notesTextMap = mutableMapOf<Int, String>()
        var deleteComments = mutableMapOf<Int, String?>()
        var listm = mutableListOf<Notes>()

        fun addNote(title: String?, text: String?): Int {
//            notesIdList.add(noteId++)
//            notesTitleMap.put(noteId, title)
//            notesTextMap.put(noteId, text)
            this.title = title
            this.text = text
            noteId = listm.lastIndex + 1
            listm.add(Notes(++noteId, title = title, text = text))
            noteId = listm.lastIndex + 1
            mapNotes.put(noteId, Notes(noteId = mapNotes.size + 1, title = title, text = text))
            return noteId
        }

        fun createComment(noteId: Int, message: String): Int {
//                comments.put(noteId,
//                    Comment(commentId = commentId + 1,
//                    noteId = noteId,
//                        message = message,
//                        deletedComment = false))
                this.message = message
                text = listm[noteId - 1].text
                title = listm[noteId-1].title
//                commentId++
//                listm.set(
//                    noteId - 1, Notes(
//                        noteId = noteId,
//                        text = listm[noteId - 1].text,
//                        title = listm[noteId-1].title,
//                        commentId = commentId + 1,
//                        message = message
//                    )
//                )
            mapNotes.set(noteId, Notes(noteId = mapNotes.getValue(noteId).noteId,
                commentId = ++commentId,
                message = message,
                text = mapNotes.getValue(noteId).text,
                title = mapNotes.getValue(noteId).title))
//            mapNotes.set(noteId, Notes(noteId = mapNotes.getValue(noteId).noteId,
//                text = mapNotes.getValue(noteId).text,
//                title = mapNotes.getValue(noteId).title), commentId = commentId + 1, message = message
                return noteId
        }
        fun deleteNote(noteId: Int): Int {
//                notesIdList.remove(noteId)
//                notesTitleMap.remove(noteId)
//                notesTextMap.remove(noteId)
            deleteComment(noteId)
            listm.removeAt(noteId - 1)
            mapNotes.remove(noteId)
            return 1
        }

        fun deleteComment(noteId:Int): Int {
//            deleteComments.put(commentId, commentsMap.get(commentId))
//            commentsMap.remove(commentId, message)
//        this.message = null
            if(mapNotes.getValue(noteId).message != null) {
                deletedComment = true
                message = null
//                listm.set(
//                    commentId - 1, Notes(
//                        noteId = noteId,
//                        text = listm[commentId - 1].text,
//                        title = listm[commentId - 1].title,
//                        deletedComment = deletedComment,
//                        message = null,
//                        commentId = commentId
//                    )
//                )
                mapNotes.set(noteId, Notes(noteId = mapNotes.getValue(noteId).noteId,
                    commentId = mapNotes.getValue(noteId).commentId,
                    message = null,
                    deletedComment = true,
                    text = mapNotes.getValue(noteId).text,
                    title = mapNotes.getValue(noteId).title)
                )
            }
            return 1
        }

        fun editNote(noteId: Int, title: String, text: String): Int {
            notesTextMap.set(noteId, text)
            notesTitleMap.set(noteId, title)
            this.title = title
            this.text = text
//            listm.set(noteId, Notes(title = title, text = text))
            mapNotes.set(noteId, Notes(noteId = mapNotes.getValue(noteId).noteId,
                commentId = mapNotes.getValue(noteId).noteId,
                message = message,
                text = text,
                title = title))
            return noteId
        }

        fun editComment(noteId: Int, message: String): Int {
            if (deletedComment == false) {
//                commentsMap.set(noteId, message)
                listm.set(commentId - 1, Notes(
                    noteId = noteId,
                    text = listm[noteId - 1].text,
                    title = listm[noteId-1].title,
                    commentId = commentId,
                    message = message))
                mapNotes.set(noteId, Notes(noteId = mapNotes.getValue(noteId).noteId,
                    commentId = mapNotes.getValue(noteId).commentId,
                    message = message,
                    deletedComment = false,
                    text = mapNotes.getValue(noteId).text,
                    title = mapNotes.getValue(noteId).title)
                )
            }
            return 1
        }

        fun restoreComment(noteId: Int): Int {
            if (mapNotes.getValue(noteId).deletedComment == true) {
//                commentsMap.put(commentId, commentsMap.get(commentId))
//                deleteComments.remove(commentId)
//        this.message = commentsMap.get(commentId)
//                deletedComment = false
                listm.set(commentId - 1,
                    Notes(noteId = noteId,
                        text = listm[commentId - 1].text,
                        title = listm[commentId-1].title,
                        commentId = commentId,
                        message = message))
                mapNotes.set(noteId, Notes(noteId = mapNotes.getValue(noteId).noteId,
                    commentId = mapNotes.getValue(noteId).commentId,
                    message = message,
                    deletedComment = false,
                    text = mapNotes.getValue(noteId).text,
                    title = mapNotes.getValue(noteId).title)
                )
            }
            return 1
        }
    fun getNotes(): MutableMap<Int, Notes>  {
        return mapNotes
    }
    fun getById(noteId: Int): Note.Notes {
        return mapNotes.getValue(noteId)
    }
    fun getCommentsById(noteId: Int): String?{
        return mapNotes.getValue(noteId).message
    }

    fun clear() {
            listm = mutableListOf()
        mapNotes = mutableMapOf()
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
    editNote(1, "bye", "bye")
    deleteComment(1)
//    println(message)
//    println(getNotes())
    restoreComment(1)
//    println(getCommentsById(1))
//    deleteNote(1)
//    println(getNotes())
//    editComment(1, "note")
    println(getNotes())
}