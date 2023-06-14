package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EUser {
    private Integer id;
    private String username;
    private String password;
    private String user_type;
    private static String currentUserType;

    public static String getCurrentUserType() {
        return currentUserType;
    }

    public static void setCurrentUserType(String currentUserType) {
        EUser.currentUserType = currentUserType;
    }

    @Override
    public boolean equals(Object o){
        if(o==null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof EUser)){
            return false;
        }
        //方便后续访问 用于比较用户的用户名和密码是否一致 但是忽略id
        EUser anotherEUser = (EUser)o;
        if(getUsername().equals(anotherEUser.getUsername())&&getPassword()
                .equals(anotherEUser.getPassword())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "EUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_type='" + user_type + '\'' +
                '}';
    }

}
