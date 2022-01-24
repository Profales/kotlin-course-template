enum class Alignment {
    LEFT,
    RIGHT,
    CENTER
}

fun alignText(
    text: String,
    lineWidth: Int = 120,
    alignment: Alignment = Alignment.LEFT
): String {
    var result = "" // aligned text
    if (text.isBlank())
        throw IllegalArgumentException("ERROR: line is empty.")
    if (lineWidth < 1)
        throw IllegalArgumentException("ERROR: line width can't be less than 1.")
    val bufList: MutableList<String> = when (alignment) {
        Alignment.LEFT -> alignmentLeft(text, lineWidth)
        Alignment.RIGHT -> alignmentRight(text, lineWidth)
        Alignment.CENTER -> alignmentCenter(text, lineWidth)
    }
    var i = 0
    while (i < bufList.size) {
        result += bufList[i]
        if (i != bufList.lastIndex)
            result += "\n"
        i++
    }
    return result
}

private fun alignmentLeft(
    text: String,
    lineWidth: Int
): MutableList<String> {
    val textList = mutableListOf<String>()
    val words = text.split(" ").toMutableList() // splitting text by words
    var i = 0
    var bufText = ""
    while (i <= words.lastIndex) {
        if (words[i].length + bufText.length < lineWidth) {
            // case 1: new word is fitting in line and clear space remains in a line
            bufText += words[i] + " "
            i++ // just take it and go to the next word
        } else if (words[i].length + bufText.length == lineWidth) {
            // case 2: new word is fitting in line without space
            bufText += words[i]
            textList.add(bufText)   // line is full, sending it to the list
            i++
            bufText = ""
        }
        // case 3: if new word (with a bufText) is too big for a line
        else if ((words[i].length + bufText.length > lineWidth) and (bufText.isEmpty())) {
            // case 3.1: bufText is empty, so this word just is too big
            // we need to cut it into pieces ([big, but will fit]\n[...]\n[small])
            var bufWord = words[i]
            while (bufWord.length > lineWidth) {
                textList.add(bufWord.substring(0, lineWidth))   // [big, but will fit]
                bufWord = bufWord.substring(lineWidth)     // [...] or [small]
            }
            bufText = if (bufText.length + bufWord.length < lineWidth) // if bufWord is not empty
                bufWord + " "
            else {
                if (bufText.isNotEmpty()) textList.add(bufText)
                bufWord
            }
            // finally, we can get rid of a hard word!
            i++
        } else {
            // case 3.2: buf is not empty and the sum with a new word won't fit in one line
            if (bufText.length + words[i].length > lineWidth) {
                // clear space for that word
                // so just send bufText in textList
                // and start typing a new component of textList
                if (bufText.isNotEmpty()) textList.add(bufText)
                bufText = ""
                // if this word is too big for a line, go check case 3.1
            }
        }
        // time to quit this loop
        if ((i > words.lastIndex) and (bufText.isNotEmpty())) {
            textList.add(bufText)
        }
    }
    return textList
}

private fun alignmentRight(
    text: String,
    lineWidth: Int
): MutableList<String> {
    val textList = alignmentLeft(text, lineWidth)
    var i = 0
    while (i <= textList.lastIndex) {
        if (textList[i].last() == ' ')
            textList[i] = textList[i].substring(0, textList[i].length - 1)
        while (textList[i].length < lineWidth)
            textList[i] = ' ' + textList[i]
        i++
    }
    return textList
}

private fun alignmentCenter(
    text: String,
    lineWidth: Int
): MutableList<String> {
    val textList = alignmentLeft(text, lineWidth)
    var i = 0
    while (i <= textList.lastIndex) {
        if (textList[i].last() == ' ')
            textList[i] = textList[i].substring(0, textList[i].length - 1)
        while (textList[i].length < lineWidth) {     // place space from the both sides
            textList[i] = ' ' + textList[i] + ' '
        }
        i++
    }
    return textList
}