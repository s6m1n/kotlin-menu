package menu.model

class Menus(menus: List<String>) {
    init {
        if (menus.size == 1) {
            require(menus.all { menu ->
                Categories.getAllMenus().contains(menu) || menu == ""
            }) { "[ERROR] 메뉴판에 존재하지 않는 메뉴입니다" }
        } else {
            require(menus.all { menu ->
                Categories.getAllMenus().contains(menu)
            }) { "[ERROR] 메뉴판에 존재하지 않는 메뉴입니다" }
        }
    }
}