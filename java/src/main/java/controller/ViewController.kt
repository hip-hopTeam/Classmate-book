package controller

import model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.portlet.ModelAndView
import service.UserService

import javax.servlet.http.HttpServletRequest

/**
 * Created by coderqiang on 2017/11/4.
 */
@Controller
class ViewController @Autowired constructor(val userService: UserService) {


    @RequestMapping("/index")
    fun toIndex(request: HttpServletRequest, map: MutableMap<String, Any>): String {
        val users = userService!!.userList
        map.put("users", users)
        return "view"
    }

    @RequestMapping("/submit")
    fun toDetail(request: HttpServletRequest, map: MutableMap<String, Any>,
                 @RequestParam("userId") userId: Long): String {
        var user=userService.get(userId)
        try {
            map.put("user", user)
        } catch (e: Exception) {
            e.printStackTrace()
            return "/error"
        }

        return "submit"
    }

    @RequestMapping("/add")
    fun toAdd(): String {
        return "add"
    }

    @RequestMapping("/toemail")
    fun toEmail(): String {
        return "email"
    }
}
