package controller

import model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import service.UserService

/**
 * Created by CoderQiang on 2017/11/4.
 */
@Controller
@RequestMapping("/user")
class UserController @Autowired constructor(val userService: UserService){

    @ResponseBody
    @RequestMapping("/add")
    fun addUser(): User {
        val user = User()
        user.phone = "13110521828"
        userService!!.addUser(user)
        return user
    }
}
