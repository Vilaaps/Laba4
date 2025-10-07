import kotlin.random.Random
fun main() {
    val board = Array(3) { Array(3) {' '}}
    var currentPlayer = 'Х'
    var moves = 0
    println("Крестики-нолики: Ты (Х) против Компьютера (0)")
    printBoard (board)
    while (true) {
        if (currentPlayer == 'X') {
            println("Твой ход! Введите строку и столбец (0, 1 или 2):")
            val row = readln().toInt()
            val col = readln().toInt()
            if (row !in 0..2 || col !in 0..2) {
                println("Неверные координаты. Повторите попытку.")
                continue
            }
            if (board[row][col] != ' ') {
                println("Ячейка уже занята. Попробуй снова.")
                continue
            }
            board[row][col] = currentPlayer
        } else {
            println("Ход компьютера:")
            val (row, col) = getComputerMove (board)
            println("Компьютер выбрал: $row $col")
            board [row][col] = currentPlayer
        }
        moves++
        printBoard (board)
        if (checkWin (board, currentPlayer)) {
            println(if (currentPlayer == 'X') "Ты победил!" else
                "Компьютер победил!")
            break
        } else if (moves == 9) {
            println("Ничья!")
            break
        }
        currentPlayer = if (currentPlayer == 'X') '0' else 'X'
    }
    println("Игра окончена.")



}
fun printBoard(board: Array<Array<Char>>){
    println("0 1 2")
    for (i in board.indices){
        print("$i ")
        println(board[i].joinToString ("|" ) {if ( it == ' ') "_" else it.toString()})
    }
    println()
}
fun checkWin (board: Array<Array<Char>>, player: Char): Boolean {
    for (i in 0..2) {

        if (board[i] [0] == player && board [i] [1] == player && board[i][2] == player) {
            return true
        }

        if (board[0][i] == player && board [1] [i] == player && board[2] [i] == player) {
            return true
        }
    }
    if (board[0] [0] == player && board [1] [1] == player && board [2] [2] == player) {
        return true
    }
    if (board[0][2] == player && board [1] [1] == player && board[2] [0] == player) {
        return true
    }
    return false
}
fun getComputerMove(board: Array<Array<Char>>): Pair <Int, Int>{
    val emptyCells = mutableListOf<Pair <Int, Int>>()
    for (i in 0..2){
        for ( j in 0..2){
            if (board [i][j] == ' '){
                emptyCells.add(Pair(i, j))
            }
        }
    }
    return emptyCells[Random.nextInt(emptyCells.size)]
}