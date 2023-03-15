package com.geekster.expensetracker.service;

import com.geekster.expensetracker.dao.UserRepo;
import com.geekster.expensetracker.dto.LoginUserDto;
import com.geekster.expensetracker.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public int registerUser(User user) {
        User userObject=userRepo.save(user);
        return userObject.getUserId();
    }

    public ResponseEntity<String> loginUser(LoginUserDto loginUser) {
        String userName=loginUser.getUserName();
        String password=loginUser.getPassword();
        List<User> isValid=userRepo.findByUserNameAndPassword(userName,password);
        if (isValid.isEmpty()){
            return new ResponseEntity<>("User name or Password doesn't match", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>("User login successfully", HttpStatus.OK);
        }
    }

    public JSONObject updateUser(User newUser, String userId) {
        JSONObject jsonObject=new JSONObject();
        User user=userRepo.findById(Integer.valueOf(userId)).get();
//        check user is present or not
        if (user!=null){
            User oldUser=user;
            newUser.setUserId(oldUser.getUserId());

            newUser.setPassword(oldUser.getPassword());
            newUser.setCreateDate(oldUser.getCreateDate());
            Timestamp updateDate=new Timestamp(System.currentTimeMillis());
            newUser.setUpdateDate(updateDate);
            userRepo.save(newUser);
        }
        else {
            jsonObject.put("errorMessage","User doesn't exist");
        }
        return jsonObject;
    }

    public void deleteUserById(int userId) {
        userRepo.deleteUserById(userId);
    }

    public JSONArray getUser(String userId) {
        JSONArray userArray=new JSONArray();
//        check if user is present or not
        if (userId !=null && userRepo.findById(Integer.valueOf(userId)).isPresent()){
            List<User> userList=userRepo.findByUserId_(Integer.valueOf(userId));
            for (User users:userList) {
                JSONObject jsonObject = setUser(users);
                userArray.put(jsonObject);
            }
        }
        else {
            List<User> userList=userRepo.findAll();
            for (User user:userList){
                JSONObject jsonObject=setUser(user);
                userArray.put(jsonObject);
            }
        }
        return userArray;
    }
    private JSONObject setUser(User user){
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("userId",user.getUserId());
        jsonObject.put("firstName",user.getFirstName());
        jsonObject.put("lastName",user.getLastName());
        jsonObject.put("userName",user.getUserName());
        jsonObject.put("password",user.getPassword());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phoneNumber",user.getPhoneNumber());
        return jsonObject;
    }
}















