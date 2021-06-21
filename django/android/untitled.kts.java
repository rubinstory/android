import java.util.Random
class Dice {
    var range: Int = 10
    	set(index: Int) { field = index }
        get() { return field }
    
    fun getPip(): Int { return Random().nextInt(range) }
}

class ScoreBoard() {
    var myDice: Dice = Dice()
    	set(dice: Dice) { field = dice }
        get() { return field }
    var top: Int = -1
    	set(value: Int) { field = value }
        get() { return field }
    var score: Array<Int> = Array(20, {0})
    	set(arr: Array<Int>) { field = arr }
    	get() { return field }
    
    fun add(value: Int) {
        print("Current Value: $value")
        if (top != -1) {
            if (value == score[top]) {
                score[++top] = value + 10
                print(" | same to previous value(add 10 > ${score[top]})")
            }
            else score[++top] = value
        }
        else score[++top] = value
        if (score[top] > 15) {
            score[top] = 15
            print(" | Bigger than 15 > change to 15")
        }
        println()
    }
    
    fun fill() {
        var i = 1
        while (i <= 20) {
            print("[${i}] ")
            add(myDice.getPip())
            i++
        }
	}
    fun print() {
        var i = 0
        println("+--------[ RESULT ]--------+")
        while (i < 20) {
            print("${score[i]} ")
            i++
        }
	}
}

fun main() {
    var scoreBoard = ScoreBoard()
    scoreBoard.fill()
    scoreBoard.print()
}