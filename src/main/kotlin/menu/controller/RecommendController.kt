package menu.controller

import menu.model.Board
import menu.model.Menus
import menu.view.InputConsole
import menu.view.OutputConsole

class RecommendController(private val inputConsole: InputConsole, private val outputConsole: OutputConsole) {

    private val board = Board(mutableMapOf())

    fun start() {
        outputConsole.startPrompt()
        initCoachNameAndAvoidMenus()
        board.applyWeedDay()
        outputConsole.recommendResultPrompt()
        board.printBoard()
        outputConsole.finishPrompt()
    }

    private fun initCoachNameAndAvoidMenus() {
        outputConsole.coachNamePrompt()
        val names = getValidName()
        names.forEach { name ->
            outputConsole.avoidMenuPrompt(name)
            val avoidMenus = getValidAvoidMenus()
            board.setCoachInfo(name, avoidMenus)
        }
    }

    private fun getValidName(): List<String> {
        return try {
            inputConsole.readCoachNames()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidName()
        }
    }

    private fun getValidAvoidMenus(): List<String> {
        return try {
            val avoidMenu = inputConsole.readAvoidMenu()
            Menus(avoidMenu)
            avoidMenu
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidAvoidMenus()
        }
    }
}