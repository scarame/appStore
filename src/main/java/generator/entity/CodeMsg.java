package generator.entity;


public class CodeMsg {

    private int code;

    private String message;

    private boolean success;


    //系统错误
    public static CodeMsg SYSTEM_NO_TOKEN = new CodeMsg(401, "未登录或登录超时");

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "操作成功");
    public static CodeMsg FAIL=new CodeMsg(100000,"操作失败");
    public static CodeMsg BIND_ERROR = new CodeMsg(100001, "参数校验异常：%s");
    public static CodeMsg SERVER_ERROR = new CodeMsg(100002, "服务端异常");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(100003, "请求非法");
    public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(100004, "访问太频繁！");

    //登录模块
    public static CodeMsg USER_NOT_LOGIN = new CodeMsg(200000, "用户没有登录,不能操作");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(200001, "登录密码不能为空");
    public static CodeMsg ACCOUNT_NOT_EXIST = new CodeMsg(200002, "账号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(200003, "密码错误");
    public static CodeMsg ACCOUNT_OR_PASSWORD_NOT_EXIST = new CodeMsg(200004, "账号或密码不正确");

    //Token
    public static CodeMsg REFRESH_TOKEN_EXPIRE = new CodeMsg(300000, "refreshToken过期");
    public static CodeMsg REFRESH_TOKEN_ERROR = new CodeMsg(300001, "refreshToken错误");


    //企业信息
    public static CodeMsg ENTERPRISE_ID_EMPTY  = new CodeMsg(500000, "企业编号不能为空");


    //用户管理
    public static CodeMsg ACCOUNT_ISNOT_EMPTY = new CodeMsg(600000, "账号不能为空");
    public static CodeMsg ACCOUNT_IS_REPEAT = new CodeMsg(600001, "账号不能重复");
    public static CodeMsg ACCOUNT_CANNOT_MODIFY = new CodeMsg(600002, "账号不能修改");


    //数据编辑
    public static CodeMsg DATA_NOFOUND = new CodeMsg(700000, "未发现数据");
    public static CodeMsg PARAM_CANNOT_EMPTY = new CodeMsg(700001, "参数不能为空");



    public CodeMsg(int code, String message ) {
        this.code = code;
        this.message = message;
        this.success = code==0 ? true: false;
    }

    public CodeMsg(int code, String msg, boolean success ) {
        this.code = code;
        this.message = msg;
        this.success = success;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.message, args);
        return new CodeMsg(code, message);
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

    public void setMessage(String msg) {
        this.message = msg;
    }

    public boolean getSuccess() { return success; }

    public void setSuccess(boolean success) { this.success = success; }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", message=" + message + "]";
    }
}

