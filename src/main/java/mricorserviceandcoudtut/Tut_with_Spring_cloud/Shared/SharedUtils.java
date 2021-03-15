package mricorserviceandcoudtut.Tut_with_Spring_cloud.Shared;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SharedUtils {
    public String generateUserId(){
        return UUID.randomUUID().toString();
    }
}
