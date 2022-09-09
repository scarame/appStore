package generator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

public class Result<T> {
    private int code;
    private boolean success;
    private String message;
    private T data;

    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success() { return new Result<T>(); }

    public static <T> Result<T> success(T data) { return new Result<T>(data); }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<T>(msg, data);
    }

    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    /**
     * 失败时候的调用
     */

    public static <T> Result<T> fail() {
        return new Result<T>(CodeMsg.FAIL);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }


    public Result() {
        this.code = 0;
        this.message = "操作成功";
        this.success = true;
    }

    public Result(T data) {
        this.code = 0;
        this.message = "操作成功";
        this.data = data;
        this.success = true;
    }

    public Result(String msg, T data) {
        this.code = 0;
        this.message = msg;
        this.data = data;
        this.success = true;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.success = true;
        this.data = data;
    }

    public Result(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMessage();
        this.success = codeMsg.getSuccess();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() { return success; }

    public void setSuccess(boolean success) { this.success = success; }
}
