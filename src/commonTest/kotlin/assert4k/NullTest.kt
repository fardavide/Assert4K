package assert4k

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertFails

internal class NullTest {

    @Test
    @JsName("WithMessage_works_on_nullability_checks")
    fun `WithMessage works on nullability checks`() {
        val e = assertFails {
            assert that "Hello" `is` Null { "That should be null!!!" }
        }
        assert that e.message equals "That should be null!!! expected null, but was:<Hello>"
    }

    @Test
    @JsName("WithMessage_works_without_expected_Null_param")
    fun `WithMessage works without expected Null param`() {
        val e = assertFails {
            assert that "Hello" `is null` { "That should be null!!!" }
        }
        assert that e.message equals "That should be null!!! expected null, but was:<Hello>"
    }
}
