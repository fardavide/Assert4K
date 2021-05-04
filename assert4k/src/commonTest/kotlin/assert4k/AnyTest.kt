package assert4k

import assert4k.anotherPackage.Invokable
import assert4k.anotherPackage.invoke
import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertFails

internal class AnyTest {

    data class A(private val s: String) {
        val isTrue get() = true
        val isFalse get() = false
    }

    data class User(val name: String, val age: Int, val cars: List<String>)

    // region with message
    @Test
    @JsName("withMessage_works_properly_with_invoke_extensions")
    fun `withMessage works properly with invoke extensions`() {
        val invokable = Invokable(0)
        val result = invokable { 5 }

        assert that result equals 5
    }
    // endregion

    // region multi assertion
    @Test
    @JsName("multi_assertion_compile_properly")
    fun `multi assertion compile properly`() {
        assert that "hello" *{
            it equals "hello"
            it contains "he"
        }

        assert that listOf(4, 5) *{
            it contains 4
            it contains 5
        }
    }

    @Test
    @JsName("multi_assertion_works_for_children")
    fun `multi assertion works for children`() {
        assert that User("Davide", 29, listOf("Car")) *{
            +name equals "Davide"
            +age() equals 29
            +cars contains "Car"
        }
    }
    // endregion

    // region reflective
    @Test
    @JsName("succeed_with_true_value")
    fun `succeed with true value`() {
        assert that A("Hello").isTrue
        assert that { A("Hello") == A("Hello") }
    }

    @Test
    @JsName("fails_with_false_value")
    fun `fails with false value`() {
        assertFails {
            assert that { A("Hello") != A("Hello") }
        }
    }

    @Test
    @JsName("WithMessage_works_on_reflective_assertion")
    fun `WithMessage works on reflective assertion`() {
        val e = assertFails {
            assert that A("Hello").isFalse / "That must be true!"
        }
        assert that e.message equals "That must be true!"
    }
    // endregion

    // region equals
    @Test
    @JsName("succeed_with_equal_objects")
    fun `succeed with equal objects`() {
        assert that A("Hello") equals A("Hello")
    }

    @Test
    @JsName("fails_with_null_actual")
    fun `fails with null actual`() {
        val nullValue: A? = null
        assertFails {
            assert that nullValue equals A("Hello")
        }
    }

    @Test
    @JsName("fails_with_different_objects")
    fun `fails with different objects`() {
        assertFails {
            assert that A("Hello") equals A("Ciao")
        }
    }
    // endregion

    // region is type
    open class Super
    class Sub: Super()
    @Test
    @JsName("is_type_success_if_same_type")
    fun `is type success if same type`() {
        assert that Super() `is` type<Super>()
    }

    @Test
    @JsName("is_type_success_if_sub_type")
    fun `is type success if sub-type`() {
        assert that Sub() `is` type<Super>()
    }

    @Test
    @JsName("is_type_fails_if_super_type")
    fun `is type fails if super-type`() {
        assert that fails {
            assert that Super() `is` type<Sub>()
        }
    }

    @Test
    @JsName("is_type_fails_if_null")
    fun `is type fails if null`() {
        val nullSuper: Super? = null
        assert that fails {
            assert that nullSuper `is` type<Super>()
        }
    }
    // endregion
}
