package ai.arturxdroid.hebrewfluffywords.domain

object WordPairs {
    val list = listOf<WordPair>(
        WordPair("to learn", "ללמוד"),
        WordPair("to speak", "לדבר")
    )

}
// image for an english textbook for a word "to learn"

data class WordPair(val english: String, val hebrew: String)