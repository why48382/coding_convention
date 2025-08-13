package org.example.coding_convention.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(true, "성공", data);
    }

    public static <T> BaseResponse<T> fail(String message) {
        return new BaseResponse<>(false, message, null);
    }
}

