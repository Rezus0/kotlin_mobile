package com.example.messengerandroid.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "culture") val culture: String?,
    @ColumnInfo(name = "born") val born: String?,
    @ColumnInfo(name = "titles") val titles: List<String> = emptyList(),
    @ColumnInfo(name = "aliases") val aliases: List<String> = emptyList(),
    @ColumnInfo(name = "played_by") val playedBy: List<String> = emptyList()
) {
    override fun toString(): String {
        return "Character(" +
                "name='${name ?: "Unknown"}', " +
                "culture='${culture ?: "Unknown"}', " +
                "born='${born ?: "Unknown"}', " +
                "titles=${if (titles.isNotEmpty()) titles.joinToString(", ") else "Unknown"}, " +
                "aliases=${if (aliases.isNotEmpty()) aliases.joinToString(", ") else "Unknown"}, " +
                "playedBy=${if (playedBy.isNotEmpty()) playedBy.joinToString(", ") else "Unknown"}" +
                ")"
    }
}
