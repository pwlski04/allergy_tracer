package com.example.tracer.shared

expect fun platformName(): String

class Greeting {
    fun greet(): String = "Hello from ${platformName()}, Tracer!"
}
