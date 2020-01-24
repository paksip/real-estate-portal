package bme.aut.szarch.realestateportal.service.kotlin



//region state pattern
abstract class StateContext() {

    private lateinit var currentState: State

    init {
        currentState = initState()
    }

    fun triggerTranzition(tranzition: Tranzition) {
        currentState.handleEvent(this, tranzition)
    }

    abstract fun initState(): State

    fun setState(nextState: State) {
        currentState = nextState
    }

}


abstract class State(
    val visitor: Visitor
) {

    open fun handleEvent(stateContext: StateContext, tranzition: Tranzition) {
        if (!visitor.isLastState()) {
            accept(tranzition)
            stateContext.setState(visitor.getNextState())
        } else {
            accept(tranzition)
        }

    }

    private fun accept(tranzition: Tranzition) {
        visitor.visit(tranzition)
    }

}

class InitPaymentState(visitor: Visitor) : State(visitor)

class GetTokenState(visitor: Visitor) : State(visitor)

class GetAccountsState(visitor: Visitor) : State(visitor)

class FinishPaymentState(visitor: Visitor) : State(visitor)

class CheckStatusState(visitor: Visitor) : State(visitor)
//endregion

//region visitor
abstract class Visitor {
    abstract fun visit(tranzition: Tranzition)
    abstract fun getNextState(): State
    abstract fun isLastState(): Boolean
}

class ErsteGetTokenVisitor() : Visitor() {

    //A Vizitor Mindig tudj, hogy mit vár, ha nem az jön azt tudja detektálni!
    override fun visit(tranzition: Tranzition) {
        if ((tranzition is DemoTranzition)) {
            println("Erste get token by ${tranzition.name}")
        } else {
            println("error")
        }
    }

    override fun getNextState(): State = GetAccountsState(ErsteGetAccountsVisitor())

    override fun isLastState() = false
}

class ErsteGetAccountsVisitor() : Visitor() {


    override fun visit(tranzition: Tranzition) {
        if ((tranzition is DemoTranzition)) {
            println("Erste get accounts by ${tranzition.name}")
        } else {
            println("error")
        }
    }

    override fun getNextState(): State = InitPaymentState(ErsteInitPaymentVisitor())

    override fun isLastState() = false
}


class ErsteInitPaymentVisitor() : Visitor() {


    override fun visit(tranzition: Tranzition) {
        if ((tranzition is DemoTranzition)) {
            println("Erste init payment by ${tranzition.name}")
        } else {
            println("error")
        }
    }

    override fun getNextState(): State = FinishPaymentState(ErsteFinishPaymentVisitor())

    override fun isLastState() = false

}

class ErsteFinishPaymentVisitor() : Visitor() {


    override fun visit(tranzition: Tranzition) {
        if ((tranzition is DemoTranzition)) {
            println("Erste finish payment by ${tranzition.name}")
        } else {
            println("error")
        }
    }

    override fun getNextState(): State = CheckStatusState(ErsteCheckStatusVisitor())
    override fun isLastState() = false
}

class ErsteCheckStatusVisitor() : Visitor() {


    override fun visit(tranzition: Tranzition) {
        if ((tranzition is DemoTranzition)) {
            println("Erste check status by ${tranzition.name}")
        } else {
            println("error")
        }
    }

    override fun getNextState(): State = FinishPaymentState(ErsteFinishPaymentVisitor())

    override fun isLastState() = true
}
//endregion

class ErsteStateMachine() : StateContext() {
    override fun initState() = GetTokenState(ErsteGetTokenVisitor())
}

fun main() {

    val ersteStateMachine = ErsteStateMachine()

    for (i in 0..10) {
        ersteStateMachine.triggerTranzition(DemoTranzition("Kristof"))
    }
}

//TODO vissza fele a kommunikáció kérdéses, hogyan kap választ a kliens egy állapot váltás után?
// ötlet observable minta a kliensek beregisztrálnaka state machinenél, és a tranzitionban van egy request, és egy responseType(konkrét visszatérési típus)
open class Tranzition()

class DemoTranzition(val name: String) : Tranzition()




