/*
 *  Copyright 2019, Liwei Wang <daveywang@live.com>.
 *  All rights reserved.
 *  Author: Liwei Wang
 *  Date: 06/2019
 */

package com.ascending.training.controller;

import com.ascending.training.model.User;
import com.ascending.training.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequestMapping(value = {"/auth"})
public class Authentication {
    private AuthService authService;

    @Autowired
    public Authentication(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestBody User user) {
        Map<String, String> token = authService.authenticate(user);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return authenticate(user);
    }
}
