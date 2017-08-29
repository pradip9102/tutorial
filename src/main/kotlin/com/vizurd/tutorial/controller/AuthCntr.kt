package com.vizurd.tutorial.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthCntr {

    @GetMapping(value = "/login")
    fun login(): String {
        return "login"
    }
}