package za.co.sita.service;

import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.transaction.UserTransaction;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import za.co.sita.dao.UserDAO;

import za.co.sita.quiz.dto.User;


@Path("/quiz")
@RequestScoped
public class QuizService {

    @Resource
    UserTransaction utx;
    
    
//=============================================Services for Users=====================================================
    @GET
    @Path("/getUser/" + "{empno}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("empno") String empno) {
        UserDAO dao = new UserDAO();

        return null;

    }
    
    
    @GET
    @Path("/viewUsers/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> viewUsers() {
        UserDAO dao = new UserDAO();
        return dao.getUsers();
    }
    

    @POST
    @Path("/register/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int register(User user) {

        UserDAO dao = new UserDAO();

        return dao.insert(user);
    }

    @PUT
    @Path("/saveUser122111/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int updateAdmin(User user) {
        UserDAO dao = new UserDAO();

        return 0;

    }

    @POST
    @Path("/login/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User login(User user) {
        UserDAO dao = new UserDAO();

        return dao.select(user);

    }




}
