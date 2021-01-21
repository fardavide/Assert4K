package assert4k

import kotlin.js.JsName
import kotlin.test.Test

internal class NullTest {

    @Test
    @JsName("WithMessage_works_on_nullability_checks")
    fun `WithMessage works on nullability checks`() {
        assert that fails {
            assert that "Hello" `is` Null / "That should be null!!!"
        } with "That should be null!!! expected null, but was:<Hello>"
    }

    @Test
    @JsName("WithMessage_works_without_expected_Null_param")
    fun `WithMessage works without expected Null param`() {
        assert that fails {
            assert that "Hello" `is null` { "That should be null!!!" }
        } with "That should be null!!! expected null, but was:<Hello>"
    }
}
