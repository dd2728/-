package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zxy
 * @create 2023/6/12 8:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EPosition {
    private Integer id;
    private String position_name;

    @Override
    public String toString() {
        return "EPosition{" +
                "id=" + id +
                ", position_name='" + position_name + '\'' +
                '}';
    }
}
