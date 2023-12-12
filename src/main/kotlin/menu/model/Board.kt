package menu.model

import camp.nextstep.edu.missionutils.Randoms

class Board(private val userBoard: MutableMap<Coach, MutableList<String>> = mutableMapOf()) {
    fun setCoachInfo(name: String, avoidMenus: List<String>) {
        userBoard[Coach(name, avoidMenus)] = mutableListOf()
    }

    fun printBoard() {
        println(
            "[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]\n" +
                    "[ 카테고리 | 한식 | 한식 | 일식 | 중식 | 아시안 ]"
        )
        userBoard.entries.forEach {
//            val recommendedMenusWithCategory = it.value.map { recommendMenu -> "${recommendMenu}(${Categories.findCategoryByMenuName(recommendMenu).label})" }
//            val final = recommendedMenusWithCategory.joinToString(separator = " | ")
//            println("[ ${it.key.name} | $final ]")
            val recommendedMenus = it.value.joinToString(separator = " | ")
            println("[ ${it.key.name} | $recommendedMenus ]")

        }
//        userBoard.entries.forEach { println("${it.key.name}가 못먹는 메뉴는 ${it.key.avoidMenu}이므로 ${it.value}를 추천합니다!") }
    }

    fun applyWeedDay() {
        repeat(5) {
            recommend()
        }
    }

    private fun recommend() {
        userBoard.forEach {
            val category = recommendCategory(it)
            val menu = getValidMenu(category, it.key, it.value)
            it.value.add(menu)
        }
    }

    private fun recommendCategory(userInfo: Map.Entry<Coach, MutableList<String>>): Categories {
        return getValidCategory(userInfo.value)
    }

    private fun getValidCategory(recommendedMenus: MutableList<String>): Categories {
        var category: Categories
        do {
            category = getCategoryRandomly()
        } while (!isValidCategory(category, recommendedMenus))
        return category
    }

    private fun getCategoryRandomly(): Categories {
        val categoryIndex = Randoms.pickNumberInRange(1, 5)
        return Categories.findCategoryByIndex(categoryIndex)
    }

    private fun isValidCategory(category: Categories, recommendedMenus: MutableList<String>): Boolean {
        var cnt = 0
        recommendedMenus.forEach { recommendedMenuName ->
            if (category == Categories.findCategoryByMenuName(recommendedMenuName)) cnt++
            if (2 <= cnt) return false
        }
        return true
    }

    private fun getValidMenu(category: Categories, coach: Coach, recommendedMenus: MutableList<String>): String {
        var randomMenu: String
        do {
            randomMenu = Randoms.shuffle(category.menus)[0]
        } while (!isValidMenu(randomMenu, recommendedMenus))
        return randomMenu
    }

    private fun isValidMenu(randomMenu: String, recommendedMenus: MutableList<String>): Boolean {
        return !recommendedMenus.contains(randomMenu)
    }
}