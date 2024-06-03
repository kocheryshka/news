package com.example.news.mapper;

import com.example.news.model.User;
import com.example.news.web.model.list.UserListResponse;
import com.example.news.web.model.single.UserResponse;
import com.example.news.web.model.upsert.UpsertUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse userToResponse(User user);

    User requestToUser(UpsertUserRequest request);

    //@Mapping(source = "userEmail", target = "email")
    User requestToUser(String userEmail, UpsertUserRequest request);

    default UserListResponse userListToUserListResponse(List<User> users){
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUserResponseList(users.stream()
                .map(this::userToResponse)
                .toList());

        return userListResponse;
    }
}
