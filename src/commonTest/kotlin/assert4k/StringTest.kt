package assert4k

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertFails

internal class StringTest {

    // region equals no case
    @Test
    @JsName("equals_not_case_succeed_with_string_with_different_case")
    fun `equals not case succeed with string with different case`() {
        assert that "Hello" `equals no case` "HELLo"
    }

    @Test
    @JsName("equals_no_case_fails_with_different_string")
    fun `equals no case fails with different string`() {
        assertFails {
            assert that "Hello" `equals no case` "Ciao"
        }
    }

    @Test
    @JsName("equals_no_case_fails_with_null_value")
    fun `equals no case fails with null value`() {
        val nullString: String? = null
        assertFails {
            assert that nullString `equals no case` "Ciao"
        }
    }
    // endregion

    // region contains
    @Test
    @JsName("contains_succeed_if_actual_contains_other")
    fun `contains succeed if actual contains other`() {
        assert that "Hello" contains "He"
    }

    @Test
    @JsName("contains_fails_with_different_case")
    fun `contains fails with different case`() {
        assert that fails {
            assert that "Hello" contains "he"
        } with "Expected <Hello> to contains <he>. It would match ignoring the case"
    }

    @Test
    @JsName("contains_fails_with_different_strings")
    fun `contains fails with different strings`() {
        assert that fails {
            assert that "Hello" contains "Ciao"
        } with "Expected <Hello> to contains <Ciao>"
    }

    @Test
    @JsName("contains_no_case_succeed_with_different_case")
    fun `contains no case succeed with different case`() {
        assert that "Hello" `contains no case` "he"
    }

    @Test
    @JsName("contains_no_case_fails_with_different_strings")
    fun `contains no case fails with different strings`() {
        assert that fails {
            assert that "Hello" `contains no case` "Ciao"
        } with "Expected <Hello> to contains <Ciao>"
    }

    @Test
    @JsName("contains_with_no_case_succeed_with_different_case")
    fun `contains with no case succeed with different case`() {
        assert that "Hello" contains ("he" no case)
    }

    @Test
    @JsName("contains_with_no_case_fails_with_different_strings")
    fun `contains with no case fails with different strings`() {
        assert that fails {
            assert that "Hello" contains ("Ciao" no case)
        } with "Expected <Hello> to contains <Ciao>"
    }
    // endregion
}
