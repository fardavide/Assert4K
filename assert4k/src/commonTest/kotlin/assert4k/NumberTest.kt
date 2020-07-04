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
}
