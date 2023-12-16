package com.ms.training.application.controller;

import com.ms.training.application.dto.AccountDTO;
import com.ms.training.application.dto.response.MessageResponse;
import com.ms.training.application.dto.response.UserDTO;
import com.ms.training.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1")
public class BaseController {
    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO, HttpSession session){
        UserDTO response = accountService.login(userDTO);
        session.setAttribute("user",userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody UserDTO userDTO, HttpSession session){
        session.removeAttribute("user");
        return new ResponseEntity<>(new MessageResponse("Success"), HttpStatus.OK);
    }

    @PostMapping("/log-out")
    public ResponseEntity<?> logout(HttpSession session){
        session.removeAttribute("user");
        return new ResponseEntity<>(new MessageResponse("Success"), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AccountDTO accountDTO, HttpSession session){
//        AccountDTO response = accountService.addAccount(accountDTO);
//        session.setAttribute("user", UserDTO.builder()
////                .roleDTO(response.getRoleDTO())
//                .is(response.getRoleId().toString())
//                .userName(response.getUsername())
//                .password(response.getPassword()).build());
        UserDTO userDTO = UserDTO.builder()
//                .userName(response.getUsername())
                .password(accountDTO.getPassword()).build();
        return login(userDTO, session);
    }
    @PostMapping("/update")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO userDTO){
        UserDTO response = accountService.updateAccount(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
