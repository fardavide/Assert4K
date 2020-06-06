package assert4k

import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertFailsWith

internal class AnyTest {

    data class A(private val s: String) {
        val isTrue get() = true
        val isFalse get() = false
    }

    // region reflective
    @Test
    fun `succeed with true value`() {
        assert that A("Hello").isTrue
        assert that { A("Hello") == A("Hello") }
    }

    @Test
    fun `fails with false value`() {
        assertFails {
            assert that { A("Hello") != A("Hello") }
        }
    }

    @Test
    fun `WithMessage works on reflective assertion`() {
        assertFails(message = "That must be true!") {
            assert that A("Hello").isFalse { "That must be true!" }
        }
    }
    // endregion

    // region equals
    @Test
    fun `succeed with equal objects`() {
        assert that A("Hello") equals A("Hello")
    }

    @Test
    fun `fails with null actual`() {
        val nullValue: A? = null
        assertFails {
            assert that nullValue equals A("Hello")
        }
    }

    @Test
    fun `fails with different objects`() {
        assertFails {
            assert that A("Hello") equals A("Ciao")
        }
    }
    // endregion
}
