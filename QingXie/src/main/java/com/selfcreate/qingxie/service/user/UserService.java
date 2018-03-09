package com.selfcreate.qingxie.service.user;

import com.selfcreate.qingxie.bean.Msg;
import com.selfcreate.qingxie.bean.user.Resume;
import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.bean.user.UserExperience;
import com.selfcreate.qingxie.exception.InvalidPasswordException;
import com.selfcreate.qingxie.exception.InvalidStudentIdException;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author evans 2018/3/8 18:25
 */

public interface UserService {
    /**
     * 获取所有User信息
     *
     * @return
     */
    List<User> getAll();

    /**
     * 根据studentid或者name登陆
     * 验证密码正确性
     *
     * @param user
     * @throws InvalidPasswordException
     * @throws InvalidStudentIdException
     */
    User login(User user) throws InvalidPasswordException, InvalidStudentIdException;

    /**
     * 根据id获取用户所有信息
     *
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * 根据id获取用户简历信息
     * 出生日期、年龄、专业班级、政治面貌、自我描述、qq手机等联系方式
     * 活动经验等
     *
     * @param userId
     * @return
     */
    Resume getResume(int userId);

    /**
     * 更新用户基本信息
     *
     * @param user
     * @return
     */
    int updateBasicInfo(User user) throws QingxieInnerException;

    /**
     * 更新用户头像
     *
     * @param record
     * @return
     */
    int updateIcon(User record);

    /**
     * 根据type判断是更新、插入、删除
     *
     * @param experience
     * @param type
     * @return
     */
    int updateUserExperience(UserExperience experience, RequestMethod type) throws QingxieInnerException;
}
