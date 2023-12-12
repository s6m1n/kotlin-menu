package menu

import menu.controller.RecommendController
import menu.view.InputConsole
import menu.view.OutputConsole

fun main() {
    val inputConsole = InputConsole()
    val outputConsole = OutputConsole()
    val recommender = RecommendController(inputConsole, outputConsole)
    recommender.start()
}