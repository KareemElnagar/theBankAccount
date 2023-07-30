var accountType = ""
var userChoice = 0
var accountBalance = IntRange(0, 1000).random()
var money = IntRange(0, 500).random()
var output = 0
var isSystemOpen = true
var option = 0


fun main() {
    println(
        "Welcome to your banking system. \n" +
                "What type of account would you like to create?\n" +
                "\n" +
                "1. Debit account\n" +

                "2. Credit account\n" +

                "3. Checking account"
    )






    while (accountType == "") {
        println("Choose an option (1,2,3)")
        userChoice = readLine()!!.toInt()
        println("The selected option is $userChoice")
        accountType = when (userChoice) {
            1 -> "Debit"
            2 -> "Credit"
            3 -> "Checking"
            else -> {
                println("Enter a valid option")
                continue
            }
        }
        println("You have created a $accountType account.")
        println("The current balance is $accountBalance dollars.")
    } 
    while (isSystemOpen) {
        println(
            "What would you like to do?\n" +
                    "1. Check bank account balance\n" +

                    "2. Withdraw money\n" +

                    "3. Deposit money\n" +

                    "4. Close the app\n" +

                    "Which option do you choose? (1, 2, 3 or 4)"
        )
        option = readLine()!!.toInt()
        println("You chose $option")
        when (option) {
            1 -> {
                println("The current balance is $accountBalance dollars.")
            }
            2 -> {
                transfer("withdraw")
            }
            3 -> {
                transfer("deposit")
            }
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue

        }
    }

    //output = withdraw(money)
    //output = debitWithdraw(money)
    //output = deposit(money)
    //output = creditDeposit(money)

}

fun withdraw(amount: Int): Int {
    accountBalance -= amount
    println("You successfully withdrew $amount")
    println("The current balance $accountBalance")
    return amount
}

fun debitWithdraw(amount: Int): Int {
    if (accountBalance == 0) {
        println("Can't withdraw, no money on this account")
        return accountBalance
    } else if (amount > accountBalance) {
        println("No enough money on this account! The checking balance is $accountBalance dollars")
        return 0
    } else {
        return withdraw(amount)
    }
}

fun deposit(amount: Int): Int {
    accountBalance += amount
    println("You successfully deposited $amount dollars")
    println("The current balance $accountBalance dollars")
    return amount
}

fun creditDeposit(amount: Int): Int {
    if (accountBalance == 0) {
        println("You don’t need to deposit anything in order to pay off the account since it has already been paid off. ")
        return accountBalance

    } else if (accountBalance + amount > 0) {
        println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is ${accountBalance} dollars.")
        return 0
    } else if (accountBalance == amount) {
        println("You deposited just enough money in order to completely pay off the account")
        return amount
    } else
        return deposit(amount)
}

fun transfer(mode: String) {
    val transferAmount: Int
    when (mode) {
        "withdraw" -> {
            transferAmount = if (accountType == "debit") {
                debitWithdraw(money)

            } else
                withdraw(money)

            println("The amount you withdrew is $transferAmount dollars.")


        }
        "deposit" -> {
            transferAmount = if (accountType == "credit") {
                creditDeposit(money)

            } else
                deposit(money)

            println("The amount you deposited is $transferAmount dollars.")

        }
        else -> return

    }

}

