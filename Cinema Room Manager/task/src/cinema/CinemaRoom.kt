package cinema

class CinemaRoom(private val _rows: Int, private val _seats: Int) {
    private var _rowN = 0
    private var _seatN = 0
    private var _mySeat = arrayListOf<Pair<Int, Int>>()
    private var _ticketsBought = 0
    private var _ticketPrice = 0
    private var _currentIncome = 0

    fun menu() {
        var option: String
        do {
            println("\n\n1. Show the seats")
            println("2. Buy a ticket")
            println("3. Statistics")
            println("0. Exit")
            option = readln()
            when(option) {
                "1" -> printRoom()
                "2" -> buyTicket()
                "3" -> statistics()
            }
        } while (option != "0")
    }

    private fun buyTicket() {

        do {
            println ("\n\nEnter a row number:")
            _rowN = readln().toInt()


            println("Enter a seat number in that row:")
            _seatN = readln().toInt()

            val freeSeat = _rowN in 1.._rows && _seatN in 1.._seats
            val seatBought = _mySeat.contains(Pair(_rowN, _seatN))

            if (!freeSeat) {
                println("Wrong input!")
                continue
            }

            if (seatBought) {
                println("That ticket has already been purchased!")
                continue
            }
            _mySeat += Pair(_rowN, _seatN)

        } while (!freeSeat || seatBought)

        val totalSeats = _rows * _seats

        if (totalSeats <= 60) {
            _ticketPrice = 10
            println("\nTicket price: $$_ticketPrice")
        } else {
            if (_rows % 2 == 0) {
                if (_rowN <= _rows / 2) {
                    _ticketPrice = 10
                    println("\nTicket price: $$_ticketPrice")
                } else {
                    _ticketPrice = 8
                    println("\nTicket price: $$_ticketPrice")
                }
            } else {
                if (_rowN <= _rows / 2) {
                    _ticketPrice = 10
                    println("\nTicket price: $$_ticketPrice")
                } else if (_rowN >= (_rows / 2) + 1) {
                    _ticketPrice = 8
                    println("\nTicket price: $$_ticketPrice")
                }
            }
        }
        _currentIncome += _ticketPrice
        ++_ticketsBought
    }

    private fun printRoom(){

        println("Cinema:")
        for (_rowN in 0.._rows) {
            for(_seatN in 0.._seats) {
                when {
                    _rowN == 0 && _seatN == 0 -> print("  ")
                    _rowN == 0 -> print(" $_seatN")
                    _seatN == 0 -> print(" $_rowN")
                    else -> {
                        print(" ")
                        print(mySeat(_rowN, _seatN))
                    }
                }
            }
            println()
        }

    }

    private fun statistics() {
        val percentage = ((_ticketsBought.toDouble() / (_rows * _seats)) * 100 )
        val formatPercentage = "%.2f".format(percentage)

        println("Number of purchased tickets: $_ticketsBought")
        println("Percentage: $formatPercentage%")
        println("Current income: $$_currentIncome")
        println("Total income: $${totalIncome()}")

    }

    private fun totalIncome(): Int {
        val totalIncome: Int
        val totalSeats = _rows * _seats

        totalIncome = if (totalSeats <= 60) {
            10 * totalSeats
        } else {
            val frontRows = totalSeats / 2
            frontRows * 10 + frontRows * 8
        }

        return totalIncome
    }

    private fun mySeat(_rowN: Int, _seatN: Int): String {
        return if (_mySeat.contains(Pair(_rowN, _seatN))) "B" else "S"

    }













}