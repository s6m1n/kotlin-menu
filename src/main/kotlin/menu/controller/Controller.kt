package menu.controller

import menu.model.Recommender
import menu.model.Menus
import menu.model.WeekCategory
import menu.view.InputConsole
import menu.view.OutputConsole

class Controller(private val inputConsole: InputConsole, private val outputConsole: OutputConsole) {

    private val weekCategory = WeekCategory()
    private val recommender = Recommender(mutableMapOf(),weekCategory.setCategory())
    fun start() {
        outputConsole.startPrompt()
        initCoachNameAndAvoidMenus()
        recommender.applyWeedDay()
        outputConsole.recommendResultPrompt()
        recommender.printRecommend()
        outputConsole.finishPrompt()
    }

    private fun initCoachNameAndAvoidMenus() {
        outputConsole.coachNamePrompt()
        val names = getValidName()
        names.forEach { name ->
            outputConsole.avoidMenuPrompt(name)
            val avoidMenus = getValidAvoidMenus()
            recommender.setCoachInfo(name, avoidMenus)
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