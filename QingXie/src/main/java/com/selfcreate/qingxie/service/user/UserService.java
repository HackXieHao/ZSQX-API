package com.selfcreate.qingxie.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfcreate.qingxie.bean.user.User;
import com.selfcreate.qingxie.bean.user.UserExample;
import com.selfcreate.qingxie.bean.user.UserExample.Criteria;
import com.selfcreate.qingxie.dao.user.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 获取所有User信息
	 * @return
	 */
	public List<User> getAll(){
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		return userMapper.selectByExample(example);
	}
	
}
