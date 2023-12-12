package menu.view

class OutputConsole {
    fun startPrompt() {
        println("점심 메뉴 추천을 시작합니다.")
    }

    fun coachNamePrompt() {
        println("\n코치의 이름을 입력해 주세요. (, 로 구분)")
    }

    fun avoidMenuPrompt(name: String) {
        println("\n${name}(이)가 못 먹는 메뉴를 입력해 주세요.")
    }

    fun recommendResultPrompt() {
        println("\n메뉴 추천 결과입니다.")
    }

    fun finishPrompt() {
        println("\n추천을 완료했습니다.")
    }
}