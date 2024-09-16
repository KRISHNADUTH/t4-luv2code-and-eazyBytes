package com.eazybytes.EazyBankBackEndApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @GetMapping("/contact")
    public String saveContactenquiryDetails() {
        return "Enquiry details saved to DB";
    }
}