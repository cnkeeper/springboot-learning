package com.cnkeep.springboot.chapter1.section1.resource;

import com.cnkeep.springboot.chapter1.section1.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述~
 *
 * @author <a href="1348555156@qq.com">LeiLi.Zhang</a>
 * @version 0.0.0
 * @date 2018/8/7
 */
@Path("/user")
@Api(value="/user", description = "Operations about user")
@Produces({"application/json", "application/xml"})
public class UserResource {
    Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    private static Map<String, User> INIT_DATA;

    static {
        INIT_DATA = new HashMap<String, User>();
        final int count = 100;
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(Integer.toString(i));
            user.setName("name:" + i);
            INIT_DATA.put(user.getId(), user);
        }
    }

    @POST
    @ApiOperation(value = "Create user",
            notes = "This can only be done by the logged in user.",
            position = 1)
    public Response createUser(
            @ApiParam(value = "Created user object", required = true) User user) {
        INIT_DATA.put(user.getId(),user);
        return Response.ok().entity("").build();
    }

    @GET
    @Path("/logout")
    @ApiOperation(value = "Logs out current logged in user session",
            position = 7)
    public Response logoutUser() {
        return Response.ok().entity("").build();
    }

    @GET
    @Path("/html/*")
    @Produces(MediaType.TEXT_HTML)
    public InputStream html(){
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("swagger-ui/index.html");
    }
}
