import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Test

class Impl1(val name: String)
class Impl2(val name: String)

interface InterfaceToMock {
    fun <OP> doThing(op: OP): OP
}

class SampleTest {

    private val thing: InterfaceToMock = mockk()

    @Test fun sampleTest_fails() {
        every { thing.doThing(any<Impl1>()) } returns Impl1("1")
        every { thing.doThing(any<Impl2>()) } returns Impl2("2")

        val result1 = thing.doThing(Impl1("op1"))
        val result2 = thing.doThing(Impl2("op2"))
    }

    @Test fun sampleTest_succeeds() {
        every { thing.doThing(any<Impl1>()) } returns Impl1("1")
        every { thing.doThing(any<Impl2>()) } returns Impl2("2")

        thing.doThing(Impl1("op1"))
        thing.doThing(Impl2("op2"))
    }

    @Test fun sampleTest_succeeds_with_slots() {
        every { thing.doThing(capture(slot<Impl1>())) } returns Impl1("1")
        every { thing.doThing(capture(slot<Impl2>())) } returns Impl2("2")

        val result1 = thing.doThing(Impl1("op1"))
        val result2 = thing.doThing(Impl2("op2"))
    }
}