package com.ellisonalves.thehotel.infrastructure.spring.rest;

import java.util.Arrays;
import java.util.List;

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
