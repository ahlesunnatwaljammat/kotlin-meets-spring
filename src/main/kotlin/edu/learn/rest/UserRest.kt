package edu.learn.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRest {
    @GetMapping( path = ["/"])
    fun index() : String{
        return "Hello World !!!!"
    }
}