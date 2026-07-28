package io.github.pwlski04.allergytracer.shared

expect fun platformName(): String

class Greeting {
    fun greet(): String = "Hello from ${platformName()}, Allergy Tracer!"
}
