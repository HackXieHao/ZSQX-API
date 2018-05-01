package com.selfcreate.qingxie.service.user;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.selfcreate.qingxie.bean.user.*;
import com.selfcreate.qingxie.dao.user.ClassInfoMapper;
import com.selfcreate.qingxie.dao.user.IconMapper;
import com.selfcreate.qingxie.dao.user.UserExperienceMapper;
import com.selfcreate.qingxie.exception.InvalidPasswordException;
import com.selfcreate.qingxie.exception.InvalidStudentIdException;
import com.selfcreate.qingxie.exception.QingxieInnerException;
import com.selfcreate.qingxie.util.EncryptionUtils;
import com.selfcreate.qingxie.util.FileUtil;
import com.selfcreate.qingxie.util.ListUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfcreate.qingxie.bean.user.UserExample.Criteria;
import com.selfcreate.qingxie.dao.user.UserMapper;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {
    final Logger logger=Logger.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IconMapper iconMapper;
    @Autowired
    private UserExperienceMapper userExperienceMapper;
    @Autowired
    private ClassInfoMapper classInfoMapper;

    private Map<String, Object> resume;
    private final String INFO_DELETE = "DELETE";
    private final String INFO_MODIFY = "MODIFY";
    private final String INFO_INSERT = "INSERT";

    /**
     * 根据多个学生的id获取User
     * @param ids
     * @return
     */
    public List<User> getUsersByIds(List<Integer> ids){
    	UserExample example = new UserExample();
    	Criteria criteria = example.createCriteria();
    	criteria.andIdIn(ids);
    	return userMapper.selectByExample(example);
    }
    
    /**
     * 更新一条记录
     * @param user
     */
    public void update(User user){
    	userMapper.updateByPrimaryKey(user);
    }
    
    /**
     * 返回所有用户信息
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        return userMapper.selectByExample(example);
    }

    @Override
    public User login(User user) {
        User result;
        String passwd = EncryptionUtils.encryptPassword(user.getPassword());
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        //判断是学生登陆还是管理员登陆
        if ("".equals(user.getStudentId()) || user.getStudentId() == null) {
            //学号栏为空,尝试作为管理员身份登陆
            criteria.andNameEqualTo(user.getName())
                    .andFlagEqualTo("A");
        } else {
            //学号不为空
            criteria.andStudentIdEqualTo(user.getStudentId());
        }
        result = ListUtil.getSingleQueryResult(userMapper.selectByExample(example));
        if (result == null) {
            logger.info("账号不存在");
            throw new InvalidStudentIdException("账号不存在");
        } else {
            if (passwd.equals(result.getPassword())) {
                //更新token、last_login_time
                try {
                    result.setToken(EncryptionUtils.getToken(result.getId()));
                    result.setLastLoginTime(new Date());
                    userMapper.updateByPrimaryKey(result);
                } catch (NoSuchAlgorithmException e) {
                    logger.error(e.getMessage());
                    throw new QingxieInnerException("服务器内部异常");
                }
                //屏蔽重要信息
                formatUserInfo(result);
                return result;
            } else {
                logger.info("密码错误");
                throw new InvalidPasswordException("密码错误");
            }
        }
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Resume getResume(int userId) {
        try {
            //构造用户基本信息
            User user = userMapper.selectByPrimaryKey(userId);
            if (user == null) {
                throw new QingxieInnerException("前端数据传输错误");
            }
            formatUserInfo(user);
            Resume resume = new Resume(user);
            if(user.getClassId()!=null){
                //班级信息
                ClassInfoExample classInfoExample = new ClassInfoExample();
                ClassInfoExample.Criteria criteria4Class = classInfoExample.createCriteria();
                criteria4Class.andIdEqualTo(user.getClassId());
                List<ClassInfo> classInfos = classInfoMapper.selectByExample(classInfoExample);
                if (classInfos != null && !classInfos.isEmpty()) {
                    resume.setClassName(classInfos.get(0).getName());
                }
            }
            //用户经历
            List<UserExperience> experiences;
            UserExperienceExample experienceExample = new UserExperienceExample();
            UserExperienceExample.Criteria criteria = experienceExample.createCriteria();
            criteria.andUserIdEqualTo(user.getId());
            experiences = userExperienceMapper.selectByExample(experienceExample);
            if (experiences != null&&!experiences.isEmpty()) {
                resume.setExperiences(experiences);
            }
            return resume;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new QingxieInnerException("服务器内部异常");
        }
    }

    /**
     * 屏蔽相关信息
     *
     * @param user
     * @return
     */
    private void formatUserInfo(User user) {
        user.setPassword(null);
        user.setLastLoginTime(null);
        user.setValidation(null);
        user.setToken(null);
    }


    /**
     * 返回0表示操作成功,返回-1表示失败，返回>0表示插入的主键
     *
     * @param experience
     * @param type
     * @return
     */
    @Override
    public int updateUserExperience(@NotNull UserExperience experience, RequestMethod type) {
        try {
            if (type.equals(RequestMethod.PUT)) {
                //修改user_experience表
                userExperienceMapper.updateByPrimaryKeySelective(experience);
                return 0;
            } else if (type.equals(RequestMethod.DELETE)) {
                //删除记录
                userExperienceMapper.deleteByPrimaryKey(experience.getId());
                return 0;
            } else {
                //插入记录
                // 需设置mybatis：useGeneratedKey="true",keyProperty="id"
                userExperienceMapper.insert(experience);
                return experience.getId();
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new QingxieInnerException("服务器内部异常");
        }
    }

    /**
     * 更新用户基本信息
     * 多表更新，需要引入事务
     *
     * @param record
     * @return
     */
    @Override
    public User updateBasicInfo(User record) {
        try {
            User updatedUser = new User(record.getId(), record.getTelephone(),
                    record.getQq(), record.getEmail(), record.getWechat());

            int update=userMapper.updateByPrimaryKeySelective(updatedUser);
            if(update>0){
                updatedUser=userMapper.selectByPrimaryKey(updatedUser.getId());
                formatUserInfo(updatedUser);
                return updatedUser;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new QingxieInnerException(e.getMessage());
        }
    }

    @Override
    public String updateIcon(@NotNull int userId, @NotNull MultipartFile icon) {
        //接收到图片，生产存储路径，然后返回存储路径
        String name = icon.getOriginalFilename();

        String savePath = FileUtil.getSavePath(userId, name);
        try (InputStream is = icon.getInputStream();
             FileOutputStream fos = new FileOutputStream(savePath)) {
            byte[] bytes=new byte[is.available()];
            is.read(bytes);
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new QingxieInnerException("服务器文件读写异常");
        }
        String accessPath = FileUtil.getAccessPath(userId, name);
        try {
            Icon iconRecord = new Icon();
            iconRecord.setIconPath(accessPath);
            iconMapper.insert(iconRecord);
            User user = new User();
            user.setId(userId);
            user.setIconId(iconRecord.getIconId());
            userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new QingxieInnerException("数据库数据库读写异常");
        }
        return accessPath;
    }

    @Override
    public String getIconUrl(int userId) {
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            Icon icon = iconMapper.selectByPrimaryKey(user.getIconId());
            if(icon==null){
                return "";
            }
            return icon.getIconPath();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new QingxieInnerException("服务器内部异常");
        }
    }

}
