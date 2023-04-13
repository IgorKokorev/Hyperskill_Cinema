package cinema

var rows = 9
var seats = 9
val hall = Array(rows) { Array(seats) {'S'} }
const val price = 10
const val discPrice = 8

const val WR_INP = "\nWrong input!"

fun main() {

    println("Enter the number of rows:")
    rows = readln().toInt()
    println("Enter the number of seats in each row:")
    seats = readln().toInt()
    if (rows !in 1..9 || seats !in 1..9) {
        println(WR_INP)
        return
    }

    do {
        println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit")
        val choice = readln().toInt()
        when(choice) {
            1 -> printHall()
            2 -> buyTicket()
            3 -> showStatistics()
            0 -> break
            else -> println(WR_INP)
        }
    } while (true)
}

fun showStatistics() {

    var numTickets = 0
    var curIncome = 0
    val totalIncome = calcTotalIncome()

    for (r in 0 until rows)
        for (s in 0 until seats) {
            if (hall[r][s] == 'B') {
                numTickets++
                curIncome += getPr(r)
            }
        }

    val perc: Double = numTickets.toDouble() * 100 / (rows * seats)
    println("Number of purchased tickets: $numTickets")
    println("Percentage: ${"%.2f".format(perc)}%")
    println("Current income: \$$curIncome")
    println("Total income: \$$totalIncome")
}

fun buyTicket() {
    var r: Int
    var s: Int
    do {
        println("\nEnter a row number:")
        r = readln().toInt()
        println("Enter a seat number in that row:")
        s = readln().toInt()

        if (r !in 1..rows || s !in 1..seats) {
            println(WR_INP)
            return
        }

        if (hall[r-1][s-1] == 'B') println("\nThat ticket has already been purchased!")
        else break
    } while (true)

    hall[r-1][s-1] = 'B'
    val pr = getPr(r-1)

    println("\nTicket price: \$$pr")
}

private fun getPr(r: Int) = if (rows * seats <= 60 || (r + 1) <= rows / 2) price
else discPrice

fun printHall() {
    print("\nCinema:\n ")
    for (c in 1..seats) print(" $c")
    println("")
    for (r in 1..rows) {
        print(r)
        for (c in 1..seats) print(" ${hall[r-1][c-1]}")
        println("")
    }
}

fun calcTotalIncome(): Int {
    val income =
        if (rows * seats <= 60) rows * seats * price
        else seats * ((rows / 2) * price + (rows - rows / 2) * discPrice)
    return income
}