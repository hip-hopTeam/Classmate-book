package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.UserService;
import util.ExcelUtil;
import util.MailUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by CoderQiang on 2017/11/4.
 */
@Controller
@RequestMapping("/user")
class UserController {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    @Autowired
    UserService userService;


    @RequestMapping("/add")
    public String addUser(@RequestParam("name")String name,
                          @RequestParam("address")String address,
                          @RequestParam("tel")String tel,
                          @RequestParam("wechatNo")String weixin,
                          @RequestParam("email")String email,
                          @RequestParam("qq")String qq,
                          @RequestParam("intro")String note,
                          Map<String,Object> map){
        try {
            User user = new User();
            user.setUserName(name);
            user.setAddress(address);
            user.setPhone(tel);
            user.setWeixin(weixin);
            user.setEmail(email);
            user.setQq(qq);
            user.setNote(note);
            if (userService.addUser(user)){
                List<User> users=userService.getUserList();
                map.put("users",users);
                return "view";
            }else {
                map.put("error","添加失败!");
                return "view";
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error","添加失败!");
            return "view";
        }
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam("userId")long userId,Map<String,Object> map) {
        try {
            boolean result=userService.deleteUser(userId);
            if (result){
                List<User> users=userService.getUserList();
                map.put("users",users);
                return "view";
            }else {
                map.put("error","添加失败!");
                return "view";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @ResponseBody
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

    @RequestMapping(value = "/update")
    public String updateUser(Map<String,Object> map,
                             @RequestParam("userId")Long userId,
                             @RequestParam("name")String name,
                             @RequestParam("address")String address,
                             @RequestParam("tel")String tel,
                             @RequestParam("wechatNo")String weixin,
                             @RequestParam("email")String email,
                             @RequestParam("qq")String qq,
                             @RequestParam("intro")String note) {
        try {
            User user = new User();
            user.setUserId(userId);
            user.setUserName(name);
            user.setAddress(address);
            user.setPhone(tel);
            user.setWeixin(weixin);
            user.setEmail(email);
            user.setQq(qq);
            user.setNote(note);
            userService.updateUser(user);
            map.put("user", user);
            return "submit";
        } catch (Exception e) {
            map.put("error", "修改失败");
            e.printStackTrace();
            return "submit";
        }
    }

    @RequestMapping(value = "/avator")
    public void addAvator(
            @RequestParam(value = "files")MultipartFile file,
            @RequestParam(value = "userId")Long userId,
            Map<String,Object> map,
            HttpServletRequest request,
            HttpServletResponse response){
        try {
            String path=request.getServletContext().getRealPath("/");
            File avator = new File(path + "/WEB-INF/image/" + file.getOriginalFilename().replaceAll(".*\\.(.*)",userId+".$1"));
            String url="/image/"+file.getOriginalFilename().replaceAll(".*\\.(.*)",userId+".$1");
            userService.addUserAvator(userId, url);
            if (!avator.getParentFile().exists()){
                avator.getParentFile().mkdirs();
            }
            file.transferTo(avator);
            response.sendRedirect("/submit?userId=" + userId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "添加头像失败");
            try {
                response.sendRedirect("/submit?userId=" + userId);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/excel")
    public String exportExcel(
            HttpServletResponse response){
        try {
            List<User> users = userService.getUserList();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-xls");
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc
            response.addHeader("Content-Disposition", "attachment;filename=classmates.xls");
            ExcelUtil.export(response.getOutputStream(), users);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/sendEmail")
    public String sendEmail(@RequestParam("content")String content,HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
            List<User> users = userService.getUserList();
            String sucess = "";
            String error = "";
            System.out.println("content"+content);
            for (User user : users) {
                try {
                    String emailRegex="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                    if (user.getEmail()==null||!user.getEmail().matches(emailRegex)){
                        error+=" "+user.getEmail();
                        continue;
                    }
                    MailUtil.smtpSSLSend(user.getEmail(),content);
                    sucess += " " + user.getEmail();
                } catch (Exception e) {
                    e.printStackTrace();
                    error+=" "+user.getEmail();
                }
            }
            return "发送成功:" + sucess + " \n发送失败:" + error;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

}