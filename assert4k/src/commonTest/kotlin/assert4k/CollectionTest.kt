package assert4k

import kotlin.js.JsName
import kotlin.test.Test

internal class CollectionTest {

    // region equals no order
    @Test
    @JsName("equals_no_order_succeed_if_has_same_elements_with_different_order")
    fun `equals no order succeed if has same elements with different order`() {
        assert that listOf(1, 2, 3) `equals no order` listOf(3, 2, 1)
    }

    @Test
    @JsName("equals_no_order_fails_if_has_different_number_of_elements")
    fun `equals no order fails if has different number of elements`() {
        assert that fails {
            assert that listOf(1, 2, 3) `equals no order` listOf(3, 2, 1, 0)
        } with "Expected Collection to contains <3, 2, 1, 0>, but contains <1, 2, 3>"
    }

    @Test
    @JsName("equals_no_order_fails_if_has_different_elements_but_same_count")
    fun `equals no order fails if has different elements but same count`() {
        assert that fails {
            assert that listOf(1, 2, 3) `equals no order` listOf(3, 2, 4)
        } with "Expected Collection to contains <3, 2, 4>, but contains <1, 2, 3>"
    }
    // endregion

    // region contains
    @Test
    @JsName("contains_succeed_if_actual_contains_other$1")
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
            val nullList: List<Int>? = null
            assert that nullList contains 7
        } with "Expected Collection to contains <7>, but is null"
    }
    // endregion

    // region contains all
    @Test
    @JsName("contains_all_succeed_if_has_same_elements_with_different_order")
    fun `contains all succeed if has same elements with different order`() {
        assert that listOf(1, 2, 3) `contains all` listOf(3, 2)
    }

    @Test
    @JsName("contains_all_succeed_with_duplicated_elements")
    fun `contains all succeed with duplicated elements`() {
        assert that listOf(1, 2, 3) `contains all` listOf(3, 2, 1, 3)
    }

    @Test
    @JsName("contains_all_fails_if_doesn_t_contain_all_the_elements")
    fun `contains all fails if doesn't contain all the elements`() {
        assert that fails {
            assert that listOf(1, 2, 3) `contains all` listOf(1, 2, 3, 4)
        } with "Expected Collection to contains <1, 2, 3, 4>, but contains <1, 2, 3>"
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
