package assert4k.anotherPackage

class Invokable(val i: Int)
operator fun Invokable.invoke(f: (Int) -> Int) =
        f(i)
