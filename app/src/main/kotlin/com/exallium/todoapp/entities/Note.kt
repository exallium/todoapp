package com.exallium.todoapp.entities

/**
 * The Note entity
 */
data class Note(val id: String = EMPTY_STRING,
                val title: String = EMPTY_STRING,
                val body: String = EMPTY_STRING,
                val created: Long = EMPTY_TIMESTAMP,
                val updated: Long = EMPTY_TIMESTAMP) {

    companion object {
        val EMPTY_STRING = ""
        val EMPTY_TIMESTAMP = -1L
    }
}
