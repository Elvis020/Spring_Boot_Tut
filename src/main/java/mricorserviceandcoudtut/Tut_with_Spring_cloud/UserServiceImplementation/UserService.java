package mricorserviceandcoudtut.Tut_with_Spring_cloud.UserServiceImplementation;

import mricorserviceandcoudtut.Tut_with_Spring_cloud.DataRequestModel.UserRequestModel;
import mricorserviceandcoudtut.Tut_with_Spring_cloud.Model.Users;

public interface UserService {
    Users createUser(UserRequestModel userDetails);
}
