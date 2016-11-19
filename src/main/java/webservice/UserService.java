package webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;

@Path("userService")
public class UserService {

    @GET
    @Produces("application/json")
    public Response getAllUsers() {
        JSONObject usersJson = new JSONObject();
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(1, "Valod", "Valodyan", "valod@mail.ru", "096-111111"));
        users.add(new User(2, "Valod2", "Valodyan2", "valod2@mail.ru", "096-111111"));
        users.add(new User(3, "Valod3", "Valodyan3", "valod3@mail.ru", "096-111111"));
        usersJson.put("users", users);
        return Response.status(200).entity(usersJson.toString()).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response add(String json) {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        try {
            User user = mapper.readValue(json, User.class);
            if (user != null) {
                jsonObject.put("message", "OK");
            } else {
                jsonObject.put("message", "NOT OK");
            }
        } catch (IOException e) {
            jsonObject.put("message", "NOT OK");
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }

}
