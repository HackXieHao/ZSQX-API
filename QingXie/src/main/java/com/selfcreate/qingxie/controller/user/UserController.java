package com.selfcreate.qingxie.controller.user;

import java.util.List;

import com.selfcreate.qingxie.bean.activity.Activity;
import com.selfcreate.qingxie.bean.user.Resume;
import com.selfcreate.qingxie.bean.user.UserExperience;
import com.selfcreate.qingxie.cache.CommonCache;
import com.selfcreate.qingxie.exception.InvalidPasswordException;
import com.selfcreate.qingxie.exception.InvalidStudentIdException;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Msg getAll() {
        try {
            List<User> users = userService.getAll();
            return Msg.success("获取成功").add("users", users);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Msg.error("获取失败").add("reason", "系统内部异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        System.out.print("ip:" + request.getRemoteAddr());
        System.out.println(">>>>用户:" + (user.getStudentId() != null ? user.getStudentId() : user.getName()) + "请求登陆");
        try {
            //验证账号密码
            user=userService.login(user);
            //身份验证成功，进一步操作
            //保存用户session
            HttpSession session = request.getSession();
            CommonCache.sessionMap.put(session.getId(), session);
            //返回sessionId
            response.setHeader("sessionId", session.getId());
            System.out.println("登陆成功！");
            return Msg.success("登陆成功").add("user", user)
                    .add("sessionId", session.getId());
        } catch (InvalidStudentIdException | InvalidPasswordException e) {
            return Msg.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Msg.error("系统异常");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public void logout(HttpServletRequest request) {
        System.out.println("ip:" + request.getRemoteAddr() + "退出登陆");
        String sessionId = request.getSession().getId();
        if (CommonCache.sessionMap.containsKey(sessionId)) {
            CommonCache.sessionMap.remove(request.getSession().getId());
        }
    }

    /**
     * 还需要进一步需要验证用户权限
     *
     * @param request
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/resume", method = RequestMethod.GET)
    public Msg getResume(HttpServletRequest request, @PathVariable("userId") int userId) {
        System.out.print(">>>>用户:" + userId + "请求获取简历");
        try {
            if (!CommonCache.sessionMap.containsKey(request.getSession().getId())) {
                return Msg.error("非法访问");
            }
            Resume resume = userService.getResume(userId);
            return Msg.success("请求成功").add("resume", resume);
        } catch (QingxieInnerException e) {
            return Msg.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/experience/update", method = RequestMethod.PUT)
    public Msg updateExperiences(HttpServletRequest request,
                                 @PathVariable("userId") String userId,
                                 @RequestBody UserExperience experience) {
        return updateExperience(experience, RequestMethod.PUT);
    }

    private Msg updateExperience(UserExperience experience, RequestMethod type) {
        try {
            int result=userService.updateUserExperience(experience, type);
            if(result==0){
                return Msg.success("更新成功");
            }else{
                return Msg.error("更新失败");
            }
        }catch (QingxieInnerException e){
            logger.error(e.getMessage());
            return Msg.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/experience/delete", method = RequestMethod.DELETE)
    public Msg deleteExperiences(HttpServletRequest request,
                                 @PathVariable("userId") String userId,
                                 @RequestBody UserExperience experience) {
        return updateExperience(experience, RequestMethod.DELETE);
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/experience/add", method = RequestMethod.POST)
    public Msg addExperiences(HttpServletRequest request,
                              @PathVariable("userId") String userId,
                              @RequestBody UserExperience experience) {
        try {
            int result=userService.updateUserExperience(experience, RequestMethod.POST);
            if(result>0){
                //添加成功,将experience的Id返回给app端
                experience.setId(result);
                return Msg.success("添加成功").add("experience", experience);
            }else{
                return Msg.error("添加失败");
            }
        }catch (QingxieInnerException e){
            return Msg.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/info/update", method = RequestMethod.PUT)
    public Msg updateBasicInfo(HttpServletRequest request,
                               @PathVariable("userId") int userId,
                               @RequestBody User user) {
        try {
            if (userId != user.getId()) {
                return Msg.error("非法请求");
            }
            if (userService.updateBasicInfo(user) > 0) {
                return Msg.success("更新成功");
            } else {
                return Msg.error("数据已是最新");
            }
        } catch (Exception e) {
            return Msg.error(e.getMessage());
        }
    }

    /**
     * 如果更新失败，返回错误码，android尝试试异步更新
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}/icon/update", method = RequestMethod.PUT)
    public Msg updateIcon(HttpServletRequest request, @PathVariable("userId") int userId) {
        return null;
    }
}
