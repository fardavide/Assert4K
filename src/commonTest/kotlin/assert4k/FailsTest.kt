package assert4k

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertFails

internal class FailsTest {

    @Test
    @JsName("fails_throw_if_no_exception_is_thrown_in_the_block")
    fun `fails throw if no exception is thrown in the block`() {
        assertFails {
            assert that fails {
                // no Exception thrown
            }
        }
    }

    @Test
    @JsName("fails_succeed_if_exception_is_thrown_in_the_block")
    fun `fails succeed if exception is thrown in the block`() {
        assert that fails {
            throw Exception()
        }
    }

    @Test
    @JsName("fails_returns_the_proper_exception")
    fun `fails returns the proper exception`() {
        val e = assert that fails {
            throw Exception("I am the exception!")
        }
        assert that e.message equals "I am the exception!"
    }

    @Test
    @JsName("fails_with_right_exception_type")
    fun `fails with right exception type`() {
        assert that fails {
            assert that fails<IllegalStateException> {
                throw Exception()
            }
        } with "Expected an exception of class java.lang.IllegalStateException to be thrown, but was java.lang.Exception"
    }

    @Test
    @JsName("check_on_exception_s_message_works_correctly")
    fun `check on exception's message works correctly`() {
        val e = assertFails {
            assert that fails {
                throw Exception()
            } with "I am the message"
        }
        assert that e.message equals "Exception's message does not match. expected:<I am the message> but was:<null>"
    }

    @Suppress("RedundantSuspendModifier")
    private suspend fun suspendFun() {
        throw Exception()
    }

    @Test
    @JsName("coFails_can_run_suspend_functions")
    fun `coFails can run suspend functions`() = blocking {
        assert that coFails {
            suspendFun()
        }
    }

    @Test
    @JsName("coFails_with_type_can_run_suspend_functions")
    fun `coFails with type can run suspend functions`() = blocking {
        assert that coFails<Throwable> {
            suspendFun()
        }
    }
}
