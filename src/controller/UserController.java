package controller;

import model.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling user-related HTTP requests
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "User Management System", tags = {"Users"})
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user
     * @param user - The user data from the request body
     * @return ResponseEntity with the created user and HTTP status
     */
    @PostMapping
    @ApiOperation(value = "Create a new user", response = User.class)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Retrieves a user by its ID
     * @param id - The user ID
     * @return ResponseEntity with the user or a not found status
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve a user by its ID", response = User.class)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves all users
     * @return ResponseEntity with a list of users
     */
    @GetMapping
    @ApiOperation(value = "Retrieve all users", response = List.class)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Updates an existing user
     * @param id - The user ID
     * @param user - The updated user data
     * @return ResponseEntity with the updated user or a not found status
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing user", response = User.class)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        user.setId(id); // Ensure the ID is set for the update
        return userService.getUser(id)
                .map(existingUser -> {
                    User updatedUser = userService.createUser(user); // Assuming createUser handles updates
                    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Deletes a user by its ID
     * @param id - The user ID
     * @return ResponseEntity with no content or a not found status
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user by its ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.getUser(id).isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
} 