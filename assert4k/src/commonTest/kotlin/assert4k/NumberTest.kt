package assert4k

import kotlin.js.JsName
import kotlin.test.Test

internal class NumberTest {

    // region equals
    @Test
    @JsName("check_equality_between_Float_and_Double")
    fun `check equality between Float and Double`() {
        assert that 5.0f equals 5.0
        assert that 5.0 equals 5.0f
        assert that 5.1f equals 5.1
        assert that 4.9 equals 4.9f
        assert that fails { assert that 5.0f equals 5.1 }
        assert that fails { assert that 4.9 equals 5.0f }
    }

    @Test
    @JsName("check_equality_between_Int_and_Double")
    fun `check equality between Int and Double`() {
        assert that 5 equals 5.0
        assert that 5.0 equals 5
        assert that fails { assert that 5 equals 5.1 }
        assert that fails { assert that 4.9 equals 5 }
    }

    @Test
    @JsName("check_equality_between_Int_and_Float")
    fun `check equality between Int and Float`() {
        assert that 5 equals 5.0f
        assert that 5.0f equals 5
        assert that fails { assert that 5 equals 5.1f }
        assert that fails { assert that 4.9f equals 5 }
    }

    @Test
    @JsName("check_equality_between_Int_and_Long")
    fun `check equality between Int and Long`() {
        assert that 5 equals 5L
        assert that 5L equals 5
    }

    @Test
    @JsName("check_equality_between_Int_and_String")
    fun `check equality between Int and String`() {
        assert that 5 equals "5"
        assert that "5" equals 5
    }

    @Test
    @JsName("check_equality_between_Int_and_UInt")
    fun `check equality between Int and UInt`() {
        assert that 5 equals 5u
        assert that 5u equals 5
    }
    // endregion

    // region between
    @Test
    @JsName("Int_between_Int_works_correctly")
    fun `Int between Int works correctly`() {
        assert that 15 between 10 .. 20
        assert that fails { assert that 5 between 10 .. 20 }
    }

    @Test
    @JsName("Float_between_Float_works_correctly")
    fun `Float between Float works correctly`() {
        assert that 1.5f between 1.0f .. 2.0f
        assert that fails { assert that 5f between 1.0f .. 2.0f }
    }

    @Test
    @JsName("Double_between_Int_works_correctly")
    fun `Double between Int works correctly`() {
        assert that 1.5 between 1 .. 2
        assert that fails { assert that 5.0 between 10 .. 20 }
    }

    @Test
    @JsName("Float_between_Int_works_correctly")
    fun `Float between Int works correctly`() {
        assert that 1.5f between 1 .. 2
        assert that fails { assert that 5.0f between 10 .. 20 }
    }
    // endregion

    // region greater than
    @Test
    @JsName("greater_between_Int")
    fun `greater between Int`() {
        assert that 10 `greater than` 5
        assert that fails { assert that 5 `greater than` 10 }
    }

    @Test
    @JsName("greater_between_Int_and_Float")
    fun `greater between Int and Float`() {
        assert that 10 `greater than` 5f
        assert that 10 `greater than` 9.9f
        assert that fails { assert that 5 `greater than` 10f }
        assert that fails { assert that 10 `greater than` 10.1f }
    }

    @Test
    @JsName("greater_between_Float")
    fun `greater between Float`() {
        assert that 10f `greater than` 5f
        assert that 10.1f `greater than` 10f
        assert that fails { assert that 5f `greater than` 10f }
        assert that fails { assert that 9.9f `greater than` 10f }
    }

    @Test
    @JsName("greater_between_Float_and_Int")
    fun `greater between Float and Int`() {
        assert that 10f `greater than` 5
        assert that 10.1f `greater than` 10
        assert that fails { assert that 5f `greater than` 10 }
        assert that fails { assert that 9.9f `greater than` 10 }
    }

    @Test
    @JsName("greater_between_Double")
    fun `greater between Double`() {
        assert that 10 `greater than` 5
        assert that 10.1 `greater than` 10
        assert that fails { assert that 5 `greater than` 10 }
        assert that fails { assert that 9.9 `greater than` 10 }
    }

    @Test
    @JsName("greater_between_Double_and_Int")
    fun `greater between Double and Int`() {
        assert that 10 `greater than` 5
        assert that 10.1 `greater than` 10
        assert that fails { assert that 5 `greater than` 10 }
        assert that fails { assert that 9.9 `greater than` 10 }
    }
    // endregion

    // region greater or equal
    @Test
    @JsName("greater_or_equal_between_Int")
    fun `greater or equal between Int`() {
        assert that 10 `greater or equal` 5
        assert that 10 `greater or equal` 10
        assert that fails { assert that 5 `greater or equal` 10 }
    }

    @Test
    @JsName("greater_or_equal_between_Float_and_Int")
    fun `greater or equal between Float and Int`() {
        assert that 10f `greater or equal` 5
        assert that 10f `greater or equal` 10
        assert that 10.1f `greater or equal` 10
        assert that fails { assert that 5f `greater or equal` 10 }
        assert that fails { assert that 9.9f `greater or equal` 10 }
    }
    // endregion

    // region less than
    @Test
    @JsName("less_between_Int")
    fun `less between Int`() {
        assert that 5 `less than` 10
        assert that fails { assert that 10 `less than` 5 }
    }

    @Test
    @JsName("less_between_Int_and_Float")
    fun `less between Int and Float`() {
        assert that 5 `less than` 10f
        assert that 10 `less than` 10.1f
        assert that fails { assert that 10 `less than` 5f }
        assert that fails { assert that 10 `less than` 9.9f }
    }

    @Test
    @JsName("less_between_Float")
    fun `less between Float`() {
        assert that 5f `less than` 10f
        assert that 9.9f `less than` 10f
        assert that fails { assert that 10f `less than` 5f }
        assert that fails { assert that 10.1f `less than` 10f }
    }

    @Test
    @JsName("less_between_Float_and_Int")
    fun `less between Float and Int`() {
        assert that 5f `less than` 10
        assert that 9.9f `less than` 10
        assert that fails { assert that 10f `less than` 5 }
        assert that fails { assert that 10.1f `less than` 10 }
    }

    @Test
    @JsName("less_between_Double")
    fun `less between Double`() {
        assert that 5 `less than` 10
        assert that 9.9 `less than` 10
        assert that fails { assert that 10 `less than` 5 }
        assert that fails { assert that 10.1 `less than` 10 }
    }

    @Test
    @JsName("less_between_Double_and_Int")
    fun `less between Double and Int`() {
        assert that 5 `less than` 10
        assert that 9.9 `less than` 10
        assert that fails { assert that 10 `less than` 5 }
        assert that fails { assert that 10.1 `less than` 10 }
    }
    // endregion

    // region less or equal
    @Test
    @JsName("less_or_equal_between_Int")
    fun `less or equal between Int`() {
        assert that 5 `less or equal` 10
        assert that 10 `less or equal` 10
        assert that fails { assert that 10 `less or equal` 5 }
    }

    @Test
    @JsName("less_or_equal_between_Float_and_Int")
    fun `less or equal between Float and Int`() {
        assert that 5f `less or equal` 10
        assert that 10f `less or equal` 10
        assert that 9.9f `less or equal` 10
        assert that fails { assert that 10f `less or equal` 5 }
        assert that fails { assert that 10.1f `less or equal` 10 }
    }
    // endregion
}
