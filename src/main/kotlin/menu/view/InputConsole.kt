package menu.view

import camp.nextstep.edu.missionutils.Console

class InputConsole {
    fun readParsedString(): List<String> {
        return Console.readLine().split(",")
    }

    fun readCoachNames(): List<String> {
        val names = readParsedString()
        listSizeException(names, 2, 5)
        elementSizeException(names, 2, 4)
        duplicatedElementException(names)
        return names
    }

    fun readAvoidMenu(): List<String> {
        val avoidMenu = readParsedString()
        listSizeException(avoidMenu, 0, 2)
        duplicatedElementException(avoidMenu)
        return avoidMenu
    }

    private fun listSizeException(list: List<Any>, startInclusive: Int, endInclusive: Int) {
        require(list.size in startInclusive..endInclusive) { "[ERROR] ${startInclusive}~${endInclusive}개의 값을 입력하세요" }
    }

    private fun elementSizeException(list: List<String>, startInclusive: Int, endInclusive: Int) {
        require(list.all { it.length in startInclusive..endInclusive }) { "[ERROR] ${startInclusive}~${endInclusive}글자로 입력하세요" }
    }

    private fun duplicatedElementException(list: List<String>) {
        require(list.size == list.distinct().size) { "[ERROR] 값이 서로 중복되지 않게 입력하세요" }
    }
}