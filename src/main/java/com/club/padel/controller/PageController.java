package com.club.padel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.club.padel.constant.ClubPadelConstant;

@RestController
@RequestMapping(ClubPadelConstant.APP_PREFIX+"/configure")
public class PageController {
    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}