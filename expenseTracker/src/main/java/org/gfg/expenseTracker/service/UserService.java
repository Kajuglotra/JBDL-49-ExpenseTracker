package org.gfg.expenseTracker.service;

import org.gfg.expenseTracker.model.User;
import org.gfg.expenseTracker.model.UserStatus;
import org.gfg.expenseTracker.repository.UserRepository;
import org.gfg.expenseTracker.request.CreateUserRequest;
import org.gfg.expenseTracker.response.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public CreateUserResponse addUser(CreateUserRequest createUserRequest) {
        User userFromDb = userRepository.findByEmailAddressJPQL(createUserRequest.getEmail());
        if(userFromDb == null){
            User user = createUserRequest.toUser();
            user.setUserStatus(UserStatus.ACTIVE);
            userFromDb = userRepository.save(user);
        }
        CreateUserResponse createUserResponse = CreateUserResponse.builder().userId(userFromDb.getId()).build();
        return createUserResponse;

    }
}
