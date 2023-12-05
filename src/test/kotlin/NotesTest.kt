import Note.addNote
import Note.commentId
import Note.createComment
import Note.deleteComment
import Note.deleteNote
import Note.editNote
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesTest {

    @Before
    fun clearBeforeTest() {
        Note.clear()
    }
    @Test
    fun addNoteTest() {
        val result = addNote("hello", "world")
        assertEquals(1, result)
    }
    @Test
    fun editNoteTest() {
        addNote("hello", "world")
        editNote(1, "oppp", "pppaaa")
        val result = true
        assertEquals(true, result)
    }
    @Test
    fun deleteNoteTest() {
        addNote("hello", "world")
        createComment(1, "wow")
        deleteNote(1)
        val result = true
        assertEquals(true, result)
    }
    @Test
    fun createCommentTest() {
        addNote("hello", "world")
        createComment(1, "wow")
        val result = commentId
        assertEquals(1, result)
    }
    @Test
    fun deleteCommentTest(){
        addNote("hello", "world")
        createComment(1, "wow")
        deleteComment(1, 1)
        val result = true
        assertEquals(true, result)
    }
    }
