package menu.model

class Coach(val name: String, val avoidMenu: List<String>) {

    init {
        validateName()
        validateAvoidMenu()
    }

    private fun validateAvoidMenu() {
        require(avoidMenu.size in 0..2) { "[ERROR] 못 먹는 메뉴는 0~2개로 입력해주세요" }
        require(avoidMenu.all { menu ->
            Categories.getAllMenus().contains(menu) || menu == ""
        }) { "[ERROR] 메뉴판에 존재하지 않는 메뉴입니다" }
    }

    private fun validateName() {
        require(name.length in 2..4) { "[ERROR] 이름은 최소 2글자, 최대 4글자로 입력하세요" }
    }
}