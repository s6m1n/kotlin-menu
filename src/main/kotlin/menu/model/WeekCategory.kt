package menu.model

import camp.nextstep.edu.missionutils.Randoms

class WeekCategory {
    fun setCategory(): List<Category> {
        val weekCategories = getValidCategory()
        return weekCategories
    }

    private fun getValidCategory(): List<Category> {
        var category: Category
        val weekCategories = mutableListOf<Category>()
        while (weekCategories.size < 5) {
            do {
                category = getCategoryRandomly()
            } while (!isValidCategory(category, weekCategories))
            weekCategories.add(category)
        }
        return weekCategories
    }

    private fun getCategoryRandomly(): Category {
        val categoryIndex = Randoms.pickNumberInRange(1, 5)
        return Category.findCategoryByIndex(categoryIndex)
    }

    private fun isValidCategory(category: Category, recommendedCategories: List<Category>): Boolean {
        return recommendedCategories.filter { it == category }.size < 2
    }
}