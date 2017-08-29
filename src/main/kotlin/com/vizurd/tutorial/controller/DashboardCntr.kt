package com.vizurd.tutorial.controller

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DashboardCntr {
    private val logger = KotlinLogging.logger { }

    @GetMapping(value = "/dashboard")
    fun showDashboard(model: Model): String {
        model.addAttribute("greeting", "Hello there!")
        return "dashboard"
    }
}