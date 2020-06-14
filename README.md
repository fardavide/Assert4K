# Assert4k

**Assert4k** is a _Kotlin Multiplatform_ assertion DSL.
It currently supports Jvm and Js only, but many other platform will come soon.



The focus of **Assert4k** is to take advantage of the power of Kotlin for create a beautiful human readable assertion DSL.
It covers a big variety of use cases and ensure you to able to write your assertion **in the same way you would write to a friend**.
Forgot about commas and parenthesis!



###### All the code is available under the same package `assert4k.*`, include it in your IDE settings for avoid to search what you need between the suggestions. 
( `Preferences -> Editor -> Code Style -> Kotlin -> Packages to Use with import '*` )



```kotlin
assert that developing `is` `fun`
```



## Usage

Include in your Gradle dependencies:
```kotlin
implementation("studio.forface:assert4k:<last_version>")
```
and start having fun!



There are many alternatives for fits everyone's tastes. Like:

* ``` `null` ``` or `Null`
* `` `not equals` `` or `notEquals`



For fail with a custom message, use `{ "This is my message" }`, it applies to almost every assertion.

```kotlin
assert that greeting equals "good night" { "It's too early for sleep!!" }
```





## Some assertions



##### General (any.kt and null.kt)

```kotlin
assert that MyClass("Hello") equals MyClass("Hello")

assert that today.isSunny

assert that { tomorrow.isSunny }

assert that myObject `is` `null`
```



##### Strings (string.kt)

```kotlin
assert that "bye bye" contains "bye"

assert that "bye bye" `contains no case` "BYE"

assert that "bye bye" `contains` ("BYE" no case)
```



##### Collections (collection.kt)

```kotlin
assert that listOf(1, 2, 3) contains 3

assert that emptyList<Int> `is` empty

assert that null `is` empty or `null`
```



##### Catch exception(fail.kt)

```kotlin
assert that fails { throw Exception() }

assert that fails<IllegalStateException> { throw IllegalStateException() }

assert that fails { throw Exception("Oops!") } with "Oops!"

assert that coFails {
  delay(100)
  throw Exception()
}
```

