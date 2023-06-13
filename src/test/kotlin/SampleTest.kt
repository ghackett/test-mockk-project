import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Test

class Impl1
class Impl2

interface InterfaceToMock {
    fun <OP> doThing(op: OP): OP
}

class SampleTest {

    private val thing: InterfaceToMock = mockk()

    @Test fun sampleTest_fails() {
        every { thing.doThing(any<Impl1>()) } returns Impl1()
        every { thing.doThing(any<Impl2>()) } returns Impl2()

        val result1 = thing.doThing(Impl1())
        val result2 = thing.doThing(Impl2())
    }

    @Test fun sampleTest_succeeds() {
        every { thing.doThing(any<Impl1>()) } returns Impl1()
        every { thing.doThing(any<Impl2>()) } returns Impl2()

        thing.doThing(Impl1())
        thing.doThing(Impl2())
    }

    @Test fun sampleTest_succeeds_with_slots() {
        every { thing.doThing(capture(slot<Impl1>())) } returns Impl1()
        every { thing.doThing(capture(slot<Impl2>())) } returns Impl2()

        val result1 = thing.doThing(Impl1())
        val result2 = thing.doThing(Impl2())
    }
}