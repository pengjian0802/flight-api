package com.pj.flightapi.common;

import com.pj.flightapi.common.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    // 成功返回的静态方法
    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .success(true)
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    // 成功返回（无数据）
    public static <T> Result<T> success() {
        return success(null);
    }

    // 成功返回的静态方法
    public static <T> Result<T> success(ResultCode resultCode) {
        return Result.<T>builder()
                .success(true)
                .code(resultCode.getCode())
                .message(resultCode.getMessage())
                .build();
    }

    // 成功返回（自定义消息）
    public static <T> Result<T> success(Integer code, String message) {
        return Result.<T>builder()
                .success(true)
                .code(code)
                .message(message)
                .build();
    }

    // 失败返回的静态方法
    public static <T> Result<T> error(ResultCode resultCode) {
        return Result.<T>builder()
                .success(false)
                .code(resultCode.getCode())
                .message(resultCode.getMessage())
                .build();
    }

    // 失败返回（自定义消息）
    public static <T> Result<T> error(Integer code, String message) {
        return Result.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }
}