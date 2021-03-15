package mricorserviceandcoudtut.Tut_with_Spring_cloud.UserServiceImplementation;

import mricorserviceandcoudtut.Tut_with_Spring_cloud.DataRequestModel.UserRequestModel;
import mricorserviceandcoudtut.Tut_with_Spring_cloud.Model.Users;
import mricorserviceandcoudtut.Tut_with_Spring_cloud.Shared.SharedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImple implements UserService{
    Map<String,Users> users;

    @Autowired
    SharedUtils sharedUtils;

    public UserServiceImple(SharedUtils sharedUtils) {
        this.sharedUtils = sharedUtils;
    }

    @Override
    public Users createUser(UserRequestModel userDetails) {
        Users createNewUser = new Users();
        createNewUser.setFirstName(userDetails.getFirstName());
        createNewUser.setLastName(userDetails.getLastName());
        createNewUser.setEmail(userDetails.getEmail());
        String userId = sharedUtils.generateUserId();
        createNewUser.setUserId(userId);


        if(users == null) users = new HashMap<>();
        users.put(userId,createNewUser);
        return createNewUser;
    }
}
