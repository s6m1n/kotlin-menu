package menu.model

class Board(val recommendBoard: MutableMap<Coach, MutableList<String>> = mutableMapOf()) {
    fun setCoachInfo(name: String, avoidMenus: List<String>) {
        recommendBoard[Coach(name, avoidMenus)] = mutableListOf()
    }

    fun printBoard() {
        recommendBoard.entries.forEach { println("${it.key.name}가 못먹는 메뉴는 ${it.key.avoidMenu}이므로 ${it.value}를 추천합니다!") }
    }
}