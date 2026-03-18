package sasu.platform.mhm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sasu.platform.mhm.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.eq("username", username);	// 这里不止可以用username，你可以自定义，主要根据你自己写的查询逻辑
//        User user = userMapper.selectOne(queryWrapper);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new UserDetailsImpl(user);	// UserDetailsImpl 是我们实现的类
        return null;
    }
}
