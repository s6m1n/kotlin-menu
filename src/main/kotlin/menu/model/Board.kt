package menu.model

import camp.nextstep.edu.missionutils.Randoms

class Board(
    private val userBoard: MutableMap<Coach, MutableList<String>> = mutableMapOf(),
    private val weekCategory: List<Category>
) {

    fun setCoachInfo(name: String, avoidMenus: List<String>) {
        userBoard[Coach(name, avoidMenus)] = mutableListOf()
    }

    fun printBoard() {
        val categoryString = weekCategory.map { it.label }.joinToString(separator = " | ")
        println(
            "[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]\n" +
                    "[ 카테고리 | $categoryString ]"
        )
        userBoard.entries.forEach {
            val recommendedMenus = it.value.joinToString(separator = " | ")
            println("[ ${it.key.name} | $recommendedMenus ]")
        }
//        userBoard.entries.forEach { println("${it.key.name}가 못먹는 메뉴는 ${it.key.avoidMenu}이므로 ${it.value}를 추천합니다!") }
    }

    fun applyWeedDay() {
        weekCategory.forEach {
            recommend(it)
        }
    }

    private fun recommend(category: Category) {
        userBoard.forEach {
            val menu = getValidMenu(category, it.key.avoidMenu, it.value)
            it.value.add(menu)
        }
    }

    private fun getValidMenu(
        category: Category,
        avoidMenus: List<String>,
        history: MutableList<String>
    ): String {
        var randomMenu: String
        do {
            randomMenu = Randoms.shuffle(category.menus)[0]
//            print("$randomMenu - ")
        } while (isInvalidMenu(randomMenu, avoidMenus, history))
        return randomMenu
    }

    private fun isInvalidMenu(
        randomMenu: String,
        avoidMenus: List<String>,
        recommendedMenus: MutableList<String>
    ): Boolean {
        val isContainAvoidMenu = avoidMenus.contains(randomMenu)
        val isDuplicated = recommendedMenus.contains(randomMenu)
        return (isDuplicated || isContainAvoidMenu)
    }
}