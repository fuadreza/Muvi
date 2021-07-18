package io.github.fuadreza.core_android.abstraction

abstract class Mapper<in I, out O> {
    abstract fun map(input: I): O
}