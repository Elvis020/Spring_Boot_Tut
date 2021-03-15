package mricorserviceandcoudtut.Tut_with_Spring_cloud.Controller;


import mricorserviceandcoudtut.Tut_with_Spring_cloud.DataRequestModel.UpdateUserRequestModel;
import mricorserviceandcoudtut.Tut_with_Spring_cloud.DataRequestModel.UserRequestModel;
import mricorserviceandcoudtut.Tut_with_Spring_cloud.Model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    Map<String,Users> users;


//    When using the requestParam, if value is a primitive integer, make sure to set the default
//    value before yu set the required boolean
    @GetMapping
    public String getUsers(
            @RequestParam(value = "page",defaultValue = "1",required = false) int page,
            @RequestParam("limit") int limit,
            @RequestParam(value = "sort",defaultValue = "desc", required = false) String sort
            ){
        return "get users was called with page = " + page + " and limit = "+ limit + " and sort =" +
                " " + sort;
    }

    @GetMapping(value = "/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<Users> getUser(@PathVariable String userId){
        if(users.containsKey(userId)){
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    },produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<Users> createUser(@Valid @RequestBody UserRequestModel userDetails){
        Users createNewUser = new Users();
        String generatedUserId = UUID.randomUUID().toString();
        createNewUser.setFirstName(userDetails.getFirstName());
        createNewUser.setLastName(userDetails.getLastName());
        createNewUser.setEmail(userDetails.getEmail());
        createNewUser.setUserId(generatedUserId);


        if(users == null) users = new HashMap<>();
        users.put(generatedUserId,createNewUser);

        return new ResponseEntity<>(createNewUser,HttpStatus.OK);
    }

    @PutMapping(path="/{userId}",consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    },produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public Users updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequestModel updateUserRequestModel ){
        Users storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(updateUserRequestModel.getFirstName());
        storedUserDetails.setLastName(updateUserRequestModel.getLastName());
        users.put(userId,storedUserDetails);
        return storedUserDetails;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        return "delete user was called";
    }

}
