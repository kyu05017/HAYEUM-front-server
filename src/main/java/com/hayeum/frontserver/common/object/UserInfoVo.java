package com.hayeum.frontserver.common.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
public class UserInfoVo {
    private String name;
    private String email;
    /* 중요 데이터 ex) 패스워드 */
    @JsonIgnore
    private String pwd;
}
