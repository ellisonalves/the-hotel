package com.ellisonalves.thehotel.infrastructure.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ellisonalves.thehotel.application.usecases.ManageGuestUseCase;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private final ManageGuestUseCase manageGuestUseCase;

    @Autowired
    public GuestController(ManageGuestUseCase manageGuestUseCase) {
        this.manageGuestUseCase = manageGuestUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> getMethodName() {
        manageGuestUseCase.findAll();
        return Arrays.asList("123", "312", "233");
    }

}
