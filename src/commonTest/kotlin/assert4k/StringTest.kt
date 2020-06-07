package assert4k

import kotlin.test.Test
import kotlin.test.assertFails

internal class StringTest {

    // region equals no case
    @Test
    fun `equals not case succeed with string with different case`() {
        assert that "Hello" `equals no case` "HELLo"
    }

    @Test
    fun `equals no case fails with different string`() {
        assertFails {
            assert that "Hello" `equals no case` "Ciao"
        }
    }

    @Test
    fun `equals no case fails with null value`() {
        val nullString: String? = null
        assertFails {
            assert that nullString `equals no case` "Ciao"
        }
    }
    // endregion

    // region contains
    @Test
    fun `contains succeed if actual contains other`() {
        assert that "Hello" contains "He"
    }

    @Test
    fun `contains fails with different case`() {
        assert that fails {
            assert that "Hello" contains "he"
        } with "Expected <Hello> to contains <he>. It would match ignoring the case"
    }

    @Test
    fun `contains fails with different strings`() {
        assert that fails {
            assert that "Hello" contains "Ciao"
        } with "Expected <Hello> to contains <Ciao>"
    }

    @Test
    fun `contains no case succeed with different case`() {
        assert that "Hello" `contains no case` "he"
    }

    @Test
    fun `contains no case fails with different strings`() {
        assert that fails {
            assert that "Hello" `contains no case` "Ciao"
        } with "Expected <Hello> to contains <Ciao>"
    }

    @Test
    fun `contains with no case succeed with different case`() {
        assert that "Hello" contains ("he" no case)
    }

    @Test
    fun `contains with no case fails with different strings`() {
        assert that fails {
            assert that "Hello" contains ("Ciao" no case)
        } with "Expected <Hello> to contains <Ciao>"
    }
    // endregion
}
