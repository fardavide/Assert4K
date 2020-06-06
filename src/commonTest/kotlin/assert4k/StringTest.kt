package assert4k

import kotlin.test.Test
import kotlin.test.assertFails

internal class StringTest {

    // region equals no case
    @Test
    fun `succeed with string with different case`() {
        assert that "Hello" `equals no case` "HELLo"
    }

    @Test
    fun `fails with different string`() {
        assertFails {
            assert that "Hello" `equals no case` "Ciao"
        }
    }

    @Test
    fun `fails with null value`() {
        val nullString: String? = null
        assertFails {
            assert that nullString `equals no case` "Ciao"
        }
    }
    // endregion
}
