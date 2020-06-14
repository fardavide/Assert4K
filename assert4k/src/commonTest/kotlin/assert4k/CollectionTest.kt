package assert4k

import kotlin.js.JsName
import kotlin.test.Test

internal class CollectionTest {

    // region contains
    @Test
    @JsName("contains_succeed_if_actual_contains_other")
    fun `contains succeed if actual contains other`() {
        assert that listOf(1, 3, 5) contains 3
    }

    @Test
    @JsName("contains_fails_if_actual_does_not_contains_other")
    fun `contains fails if actual does not contains other`() {
        assert that fails {
            assert that listOf(1, 3, 5) contains 7
        } with "Expected Collection to contains <7>, but contains <1, 3, 5>"
    }

    @Test
    @JsName("contains_fails_if_actual_is_null")
    fun `contains fails if actual is null`() {
        assert that fails {
            assert that null contains 7
        } with "Expected Collection to contains <7>, but is null"
    }
    // endregion

    // region is empty
    @Test
    @JsName("is_empty_succeed_if_actual_is_empty")
    fun `is empty succeed if actual is empty`() {
        assert that emptyList<Int>() `is` empty
    }

    @Test
    @JsName("is_empty_fails_if_actual_contains_some_element")
    fun `is empty fails if actual contains some element`() {
        assert that fails {
            assert that listOf(1, 3, 5) `is` empty
        } with "Expected Collection to be empty, but has 3 elements"
    }

    @Test
    @JsName("is_empty_fails_if_actual_is_null")
    fun `is empty fails if actual is null`() {
        assert that fails {
            val nullList: List<Int>? = null
            assert that nullList `is` empty
        } with "Expected Collection to be empty, but is null"
    }
    // endregion

    // region is null or empty
    @Test
    @JsName("is_null_or_empty_succeed_if_actual_is_empty")
    fun `is null or empty succeed if actual is empty`() {
        assert that emptyList<Int>() `is` `null or empty`
    }

    @Test
    @JsName("is_null_or_empty_succeed_if_actual_is_null")
    fun `is null or empty succeed if actual is null`() {
        val nullList: List<Int>? = null
        assert that nullList `is` `null or empty`
    }

    @Test
    @JsName("is_null_or_empty_fails_if_actual_contains_some_element")
    fun `is null or empty fails if actual contains some element`() {
        assert that fails {
            assert that listOf(1, 3, 5) `is` `null or empty`
        } with "Expected Collection to be empty, but has 3 elements"
    }
    // endregion

    // region is not empty
    @Test
    @JsName("is_not_empty_succeed_if_actual_is_not_empty")
    fun `is not empty succeed if actual is not empty`() {
        assert that listOf(1, 2, 3) `is not` empty
    }

    @Test
    @JsName("is_not_empty_fails_if_actual_does_not_contain_any_element")
    fun `is not empty fails if actual does not contain any element`() {
        assert that fails {
            assert that emptyList<Int>() `is not` empty
        } with "Expected Collection to not be empty, but is empty"
    }

    @Test
    @JsName("is_not_empty_fails_if_actual_is_null")
    fun `is not empty fails if actual is null`() {
        assert that fails {
            val nullList: List<Int>? = null
            assert that nullList `is not` empty
        } with "Expected Collection to not be empty, but is null"
    }
    // endregion

}
