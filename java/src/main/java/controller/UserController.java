package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by CoderQiang on 2017/11/4.
 */
@RestController
@RequestMapping("/user")
class UserController {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam("userId")long userId) {
        try {
            boolean result=userService.deleteUser(userId);
            if (result)
                return SUCCESS;
            else return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

    }

    @RequestMapping(value = "/all")
    public List<User> getAllUser(){
        try {
            List<User> users = userService.getUserList();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/avator")
    public String addAvator(
            @RequestParam(value = "files")MultipartFile file,
            @RequestParam(value = "userId")String userId,
            HttpServletRequest request){
        try {

            String path=request.getServletContext().getRealPath("/");
            File avator = new File(path + "/WEB-INF/image/" + file.getOriginalFilename().replaceAll(".*\\.(.*)",userId+".$1"));
            file.transferTo(avator);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}