package com.cose.easywu.service.impl;

import com.cose.easywu.exception.UserException;
import com.cose.easywu.mapper.UserMapper;
import com.cose.easywu.po.Admin;
import com.cose.easywu.po.User;
import com.cose.easywu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void regist(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public boolean checkEmailExist(String email) {
        String id = userMapper.selectByEmail(email);
        if (id != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkNickExist(String nick) {
        String id = userMapper.selectByNick(nick);
        if (id != null) {
            return true;
        }
        return false;
    }

    @Override
    public void updateUserState(String u_id, String u_code, int u_state) {
        User user = new User();
        user.setU_id(u_id);
        user.setU_code(u_code);
        user.setU_state(u_state);
        userMapper.updateUserState(user);
    }

    @Override
    public Admin login_tzsc(String username, String pwd) {
        Admin admin = new Admin(username, pwd);
        admin = userMapper.selectTzscIdByUsernameAndPwd(admin);
        return admin;
    }

    @Override
    public boolean addTzscNotification(String admin_id, String title, String content, Date time) {
        Map<String, String> map = new HashMap<>();
        map.put("admin_id", admin_id);
        map.put("title", title);
        map.put("content", content);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("time", formatter.format(time));
        int id = userMapper.insertTzscNotification(map);
        if (id > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String login(String u_email, String u_pwd) throws UserException {
        User user = new User();
        user.setU_email(u_email);
        user.setU_pwd(u_pwd);
        String u_id = userMapper.selectIdByEmailAndPwd(user);
        if (u_id == null || u_id.equals("")) {
            throw new UserException("邮箱或密码错误");
        }
        int state = userMapper.selectStateById(u_id);
        if (state == 0) {
            throw new UserException("该账号尚未激活");
        }
        return u_id;
    }

    @Override
    public void saveEmailCode(String u_email, String u_code) {
        User user = new User();
        user.setU_email(u_email);
        user.setU_code(u_code);
        userMapper.updateUserCodeByEmail(user);
    }

    @Override
    public boolean checkEmailCode(String u_email, String u_code) {
        User user = new User();
        user.setU_email(u_email);
        user.setU_code(u_code);
        String u_id = userMapper.selectByEmailAndCode(user);
        if (u_id == null || u_id.equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean resetPwd(String u_email, String u_pwd) {
        User user = new User();
        user.setU_email(u_email);
        user.setU_pwd(u_pwd);
        userMapper.updateUserPwdByEmail(user);
        String u_id = userMapper.selectIdByEmailAndPwd(user);
        if (u_id == null || u_id.equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public User getUserInfo(String u_id) {
        return userMapper.selectById(u_id);
    }

    @Override
    public boolean editPwd(String u_email, String u_newpwd, String u_oldpwd) {
        User user = new User();
        user.setU_email(u_email);
        user.setU_pwd(u_oldpwd);
        String u_id = userMapper.selectIdByEmailAndPwd(user);
        if (u_id == null || u_id.equals("")) {
            return false;
        }
        // 修改密码
        user.setU_pwd(u_newpwd);
        userMapper.updateUserPwdByEmail(user);
        return true;
    }

    @Override
    public void editSex(String u_id, int u_sex) {
        User user = new User();
        user.setU_id(u_id);
        user.setU_sex(u_sex);
        userMapper.updateSexById(user);
    }

    @Override
    public void savePhoto(String u_id, String photoName) {
        User user = new User();
        user.setU_id(u_id);
        user.setU_photo(photoName);
        System.out.println(user.toString());
        userMapper.updatePhotoById(user);
    }
}
