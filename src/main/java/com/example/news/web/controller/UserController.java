package com.example.news.web.controller;

import com.example.news.mapper.UserMapper;
import com.example.news.model.User;
import com.example.news.service.UserService;
import com.example.news.utils.BeanUtils;
import com.example.news.web.model.list.UserListResponse;
import com.example.news.web.model.single.UserResponse;
import com.example.news.web.model.upsert.UpsertUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(){
        return ResponseEntity.ok(userMapper.userListToUserListResponse(userService.findAll()));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> findById(@PathVariable String email){
        //System.out.println("id is " + email);
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(email)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UpsertUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(userService.save(userMapper.requestToUser(request))));
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserResponse> update(@PathVariable String email, @RequestBody UpsertUserRequest request){
        User existingUser = userService.findById(email);
        User user = userMapper.requestToUser(request);
        BeanUtils.copyNonNullProperties(user, existingUser);

        return ResponseEntity.ok(userMapper.userToResponse(userService.update(existingUser)));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email){
        userService.deleteById(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
