package menu

import menu.controller.Controller
import menu.view.InputConsole
import menu.view.OutputConsole

fun main() {
    val inputConsole = InputConsole()
    val outputConsole = OutputConsole()
    val recommender = Controller(inputConsole, outputConsole)
    recommender.start()
}

/**
소민,솜소,이소,민민
규동,우동
규동,우동
규동,우동
규동,우동

하이라이스,뇨끼
미소시루,스시
가츠동,오니기리
 **/