package com.amy.mbg.gen.common.entity;

import com.amy.mbg.gen.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 张咪
 * @history 2020/2/13 新建
 * @since JDK1.7
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    private int code = ResultEnum.SUCCESS.code;

    private String msg = ResultEnum.SUCCESS.msg;

    private Object data;

    public static Result success(){
       return success(null);
    }

    public static Result success(Object data){
        return success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.msg,data);
    }

    public static Result success(int code,String msg,Object data){
        return new Result(code,msg,data);
    }


    public static Result fail(){
        return fail(ResultEnum.FAIL.code,ResultEnum.FAIL.msg);
    }

    public static Result fail(int code,String msg){
       return fail(code,msg,null);
    }

    public static Result fail(int code,String msg,Object data){
        return new Result(code,msg,data);
    }
}
