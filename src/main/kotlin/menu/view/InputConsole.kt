package menu.view

import camp.nextstep.edu.missionutils.Console

class InputConsole {
    fun readSplittedString(): List<String> {
        return Console.readLine().split(",")
    }
}