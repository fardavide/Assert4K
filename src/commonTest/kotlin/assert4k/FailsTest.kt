package assert4k

import kotlin.test.Test
import kotlin.test.assertFails

internal class FailsTest {

    @Test
    fun `fails throw if no exception is thrown in the block`() {
        assertFails {
            assert that fails {
                // no Exception thrown
            }
        }
    }

    @Test
    fun `fails succeed if exception is thrown in the block`() {
        assert that fails {
            throw Exception()
        }
    }

    @Test
    fun `fails returns the proper exception`() {
        val e = assert that fails {
            throw Exception("I am the exception!")
        }
        assert that e.message equals "I am the exception!"
    }

    @Test
    fun `fails with right exception type`() {
        assert that fails {
            assert that fails<IllegalStateException> {
                throw Exception()
            }
        } with "Expected an exception of class java.lang.IllegalStateException to be thrown, but was java.lang.Exception"
    }

    @Test
    fun `check on exception's message works correctly`() {
        val e = assertFails {
            assert that fails {
                throw Exception()
            } with "I am the message"
        }
        assert that e.message equals "Exception's message does not match. expected:<I am the message> but was:<null>"
    }
}
