import com.revature.controllers.EmployeeController;
import com.revature.controllers.FlashCardController;
import com.revature.controllers.ManagerController;
import com.revature.controllers.UserController;
import com.revature.services.UserService;
import io.javalin.Javalin;

public class Driver {


    public static void main(String[] args){

        Javalin app = Javalin.create().start(8080);

        UserService userService = new UserService();
        UserController userController = new UserController(userService);

        FlashCardController cardController = new FlashCardController();

        EmployeeController employee = new EmployeeController();

        ManagerController managerController = new ManagerController();

        app.get("/", context ->
                context.json("{" +
                "message: hello world  }")


        );

        //Employee uris and handlers

        app.get("/employee/login", employee.login);
        app.post("/employee/register", employee.register);
        app.post("/employee/create-tickets", employee.addTicket);
        app.get("/employee/view-tickets/{id}", employee.viewMyTickets);

        //Manager uris

        app.get("/manager/login", managerController.login);
        app.post("/manager/register", managerController.register);
        app.get("/manager/view-tickets", managerController.getAll);
        app.get("/manager/view-tickets/{id}", managerController.viewTicket);
        app.put("/manager/approve/{id}", managerController.approve);
        app.put("/manager/deny/{id}", managerController.deny);
        app.get("/manager/view-pending", managerController.viewPending);
        app.get("/manager/view-approved", managerController.viewApproved);
        app.get("/manager/view-denied", managerController.viewDenied);



        //users uris
        app.get("/users",userController.getAllUsers);
        app.get("/user/{id}",userController.getUserById);
        app.post("/user",userController.createNewUser);
        app.put("/user",userController.updateUser);
        app.delete("/user", userController.deleteUser);
        app.delete("/user/{id}", userController.deleteUserById);

        //flashcard uris
        app.get("/flashcards", cardController.createNewFlashCard);
        app.post("/flashcard",cardController.createNewFlashCard);

//        app.get("/employee/login", )

//        /**
//         * public: Available anywhere
//         * protected: Access within the same package and the class's subclasses
//         * default: Access only within the same package.
//         * private: Access only within the same class.
//         */

        //




    }


}
