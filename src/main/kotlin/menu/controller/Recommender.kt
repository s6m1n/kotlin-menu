package menu.controller

import menu.model.Board
import menu.view.InputConsole
import menu.view.OutputConsole

class Recommender(private val inputConsole: InputConsole, private val outputConsole: OutputConsole) {

    private val board = Board(mutableMapOf())

    fun start() {
        outputConsole.startPrompt()
        initCoach()
    }

    private fun initCoach() {
        val names = getValidCoachNames()
        names.forEach { name ->
            val avoidMenus = getAvoidMenus(name)
            board.setCoachInfo(name, avoidMenus)
        }
        board.printBoard()
    }

    private fun getValidCoachNames(): List<String> {
        outputConsole.coachNamePrompt()
        val names = inputConsole.readSplittedString()
        validateCoachNames(names)
        return names
    }

    private fun validateCoachNames(names: List<String>) {
        require(names.size in 2..5) { "[ERROR] 최소 2명, 최대 5명의 이름을 입력하세요" }
        require(names.size == names.distinct().size) { "[ERROR] 이름은 중복될 수 없습니다" }
        require(names.all { name -> name.length in 2..4 }) { "[ERROR] 이름은 최소 2글자, 최대 4글자로 입력하세요" }
    }

    private fun getAvoidMenus(name: String): List<String> {
        outputConsole.avoidMenuPrompt(name)
        val avoidMenus = inputConsole.readSplittedString()
        require(avoidMenus.size == avoidMenus.distinct().size) { "[ERROR] 못 먹는 메뉴는 중복될 수 없습니다" }
        return avoidMenus
    }
}