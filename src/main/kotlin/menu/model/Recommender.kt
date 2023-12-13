package menu.model

import camp.nextstep.edu.missionutils.Randoms

class Recommender(
    private val userInfo: MutableMap<Coach, MutableList<String>> = mutableMapOf(),
    private val weekCategory: List<Category>
) {

    fun setCoachInfo(name: String, avoidMenus: List<String>) {
        userInfo[Coach(name, avoidMenus)] = mutableListOf()
    }

    fun printRecommend() {
        val categoryString = weekCategory.map { it.label }.joinToString(separator = " | ")
        println(
            "[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]\n" +
                    "[ 카테고리 | $categoryString ]"
        )
        userInfo.entries.forEach {
            val recommendedMenus = it.value.joinToString(separator = " | ")
            println("[ ${it.key.name} | $recommendedMenus ]")
        }
    }

    fun applyWeedDay() {
        weekCategory.forEach {
            recommend(it)
        }
    }

    private fun recommend(category: Category) {
        userInfo.forEach {
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