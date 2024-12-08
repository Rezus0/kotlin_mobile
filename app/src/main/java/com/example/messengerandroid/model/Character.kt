package com.example.messengerandroid.model

data class Character(
    var name: String? = null,
    var culture: String? = null,
    var born: String? = null,
    var titles: List<String>? = null,
    var aliases: List<String>? = null,
    var playedBy: List<String>? = null
) {
    override fun toString(): String {
        return "Character(" +
                "name='${name ?: "Unknown"}', " +
                "culture='${culture ?: "Unknown"}', " +
                "born='${born ?: "Unknown"}', " +
                "titles=${titles?.joinToString(", ") ?: "Unknown"}, " +
                "aliases=${aliases?.joinToString(", ") ?: "Unknown"}, " +
                "playedBy=${playedBy?.joinToString(", ") ?: "Unknown"}" +
                ")"
    }
}
