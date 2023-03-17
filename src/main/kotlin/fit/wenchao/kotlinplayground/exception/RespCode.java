package fit.wenchao.kotlinplayground.exception;

public enum RespCode {
    SUCCESS("10000", "success"),
    UPDATE_SUCCESS("10001", "update success"),
    INSERT_SUCCESS("10002", "insert success"),

    AUTH_FAILED("10010", "auth failed"),
    AUTH_USER_NOT_EXISTS("10011", "user not exists"),
    LDAP_UPDATE("10012", "Ldap data updated, auth failed"),

    FRONT_END_PARAMS_ERROR("10020", "front end params error"),

    TOTP_GEN_FAILED("10030", "totp generation failed"),
    USER_NOT_PERMIT_TOTP("10031", "user not permit totp"),


    TOKEN_JWT_SIGN_ERROR("10040", "jwt sign error"),
    TOKEN_JWT_EXPIRED("10041", "jwt expired"),
    TOKEN_JWT_NOT_MATCH_REQUIREMENT("10042", "jwt not match requirement"),
    TOKEN_JWT_FORMAT_ERROR("10043", "jwt format error"),
    TOKEN_JWT_BODY_ERROR("10044", "jwt body error"),
    TOKEN_JWT_ERROR("10045", "jwt error"),
    TOKEN_MISSING("10046", "token header missing"),


    GENERAL_ERROR("10050", "general error"),
    NO_DATA("10051", "no data"),
    UPDATE_NO_CHANGE("10052", "update no change"),
    INSERT_EXISTS("10053", "insert exists"),


    NO_DEVICE("10060", "device not exists"),
    PSK_ERROR("10061", "device psk error"),

    NOT_APP_OWNER("", "不是App的所有者"),
    APP_CAN_NOT_ACCESS("10080", "app can not access"), NO_APP("10081", "app not exists"),
    ACCESS_TOKEN_MISSING("10082", "app access token missing"),
    ACCESS_TOKEN_INVALID("10083", "AccessToken Invalid"),
    USER_DATA_ERROR("10090", "user data error"),

    NO_DEPT("10100", "dept not exists"),

    ROLE_EXISTS("10110", "role already exists"),
    CAN_NOT_ADD_SUPER_ADMIN_TO_USER("", "不能将超级管理员角色添加给用户"),
    NO_ROLE("10111", "role not exists"),
    CAN_NOT_MODIFY_SUPER_ADMIN_NAME("", "不能修改超级管理员角色名称"),
    CAN_NOT_REMOVE_SUPER_ADMIN("", "不能删除超级管理员"),


    IDEM_TOKEM_MISSING("10120", "Idempotency token missing"),

    REPEAT_REQUEST("10130", "Request repeat"),
    API_ACCESS_TOO_FREQUENT("10140", "API access is too frequent, and access is forbidden"),


    MENU_EXSITS("10150", "菜单项已存在"),
    ROLE_MENU_EXISTS("10151", "菜单已在角色中"),

    NO_MENU("10152", "菜单项不存在"),
    NO_MENU_IN_ROLE("10153", "菜单不在角色中"),


    NO_PERMISSION("10160", "权限不存在"),
    PERMISSION_NOT_REQUIRED("10161", "用户无权访问此接口"),
    PERMISSION_EXISTS_IN_ROLE("10162", "角色已经有该权限"),
    PERMISSION_EXISTS("10163", "权限已存在"),


    API_CONFIG_AVAILABLE("10170", "API 没有正确配置，暂时不可用"),
    NO_API("10171", "API 不存在"),
    API_EXISTS("10172", "API 已存在"),


    API_EXISTS_IN_PERMISSION("10172", "API 已存在权限中"),


    DEVICE_SERIAL_NUMBER_DUPLICATE("", "设备序列号重复"),

    DEVICE_TEMPLATE_EXISTS("10180", "设备模板已存在"),
    NO_ENTRY_DATA_TYPE("10182", "设备模板数据类型不存在"),
    TEMPLATE_REQUIRED_ENTRY_NOT_EXISTS("10182", "设备模板必填字段不存在"),
    TEMPLATE_HAS_DEVICE_INSTANCE("10183", "模板存在设备实例，不能删除"),
    DEVICE_ENTRY_DUPLICATE("10184", "设备的条目重复"),
    TEMPLATE_ENTRY_NOT_EXISTS("10185", "设备模板条目不存在"),
    NO_DEVICE_TEMPLATE("10186", "设备模板不存在"),
    ENTRY_NOT_EXISTS_IN_TEMPLATE("10187", "条目不存在设备模板中"),
    NO_ENTRY_IN_DEVICE("10188", "条目不存在设备中"),
    UPDATE_DATA_TYPE_ID_OF_ENTRY_NOT_SUPPORT("UPDATE_DATA_TYPE_ID_OF_ENTRY_NOT_SUPPORT", "不支持更新模板条目的数据类型"),


    DEVICE_HAS_NOT_PSK_ATTR("", "设备没有预共享秘钥属性"),
    NO_ATTRBUTE_IN_DEVICE("", "设备中没有指定属性"),
    DEVICE_REQUIRED_ENTRY_MISSING("", "设备必填字段缺失"),
    TEMPLATE_ENTRY_NAME_DUPLICATE("", "设备模板字段名重复"),

    CAN_NOT_MODIFY_TEMPLATE_REQUIRED_ENTRY("","不能修改设备必填字段" ),


    UPLOAD_FILE_SIZE_EXCEED_UPPER_LIMIT("", "上传的文件大小大于上限"),
    UPLOAD_FILE_UNKNOWN_ERROR("", "上传文件过程中发生未知错误"),
    FILE_NAME_DUPLICATE("","文件名重复，请更名再上传" ),
    FILE_PATH_ERROR("", "文件路径错误"), FILE_EXISTS("","文件已存在" ),
    FILE_CREATE_ERROR("", "文件创建失败"), FILE_VERSION_EXISTS("", "文件版本已存在"),
    NO_ARTIFACT("","artifact 不存在"),
    FILE_CONSISTENCY_ERROR("", "文件一致性错误"),
    FILE_VERSION_NOT_FOUND("", "文件版本信息不存在"),
    NO_FILE("", "文件不存在"),
    FILE_IS_NOT_DIR("", "指定的文件不是目录"),
    ARTIFACT_EXISTS("", "artifact名重复"), CREATE_ARTIFACT_FAIL("", "创建Artifact失败"),
    UPLOAD_FILE_CONTAINER_FOLDER_NOT_EXISTS("", "上传文件所在的目录不存在"),
    ARTIFACT_TEMPLATE_EXISTS("", "Artifact与Template关联已经存在"),
    DEVICE_TEMPLATE_NOT_EXISTS("", "设备模板不存在"),
    ART_OP_NOT_PERMIT("", "无权对此Artifact操作"),
    DOWNLOAD_TOKEN_INVALID("", "下载token无效"),
    ART_SHARE_TYPE_NOT_SUPPORT("", "Artifact 共享类型不支持"), UPLOAD_TOKEN_INVALID("", "上传token无效"),
    FILE_TOKEN_INVALID("", "文件操作Token无效"),
    OPERATION_INVALID("", "非法操作"),
    API_NOT_OPEN("", "接口不开放"),
    CREATE_TEMP_FILE_ERROR("", "创建temp文件失败"),
    ARCHIVE_DIR_ERROR("", "Archive 目录失败"),
    ART_SHARE_PERMISSION_NOT_EXISTS("", "Artifact 共享权限"),
    DEFAULT_ROLE_NOT_CONFIGURED("", "默认角色没有正确配置"),
    ADMIN_ROLE_NOT_CONFIGURED("", "超级管理员角色没有正确配置"),
    CAN_NOT_MODIFY_DEFAULT_ROLE_NAME("", "不能修改默认角色名"),
    NO_FILE_TYPE("", "文件类型错误"), JWT_CONFIG_ERROR("", "JWT未正确配置"),

    FILE_NAME_EMPTY("", "文件名为空"),
    EMPTY_FILE("", "文件内容为空"),

    UPLOAD_FILE_MISSING("", "上传文件缺失"),
    NO_DEVICE_LIB("", "设备lib不存在"),
    DEVICE_LIB_TOO_LARGE("", "设备Lib太大"),
    PARAM_VALUE_NOT_DEFINED("", "参数值未定义"),

    DEVICE_LIB_NAME_DULPLICATE("", "设备Lib名重复"),
    CAN_NOT_REMOVE_PRE_CONFIG_ROLE("", "不能删除预置角色"),
    BUSINESS_BUSY_TRY_AGAIN("", "业务繁忙，稍后再试");


    @Deprecated
    private String code;

    private String msg;

    public String getCode() {
        return name();
    }

    public String getMsg() {
        return msg;
    }

    RespCode(String code, String message) {
        this.code = code;
        this.msg = message;
    }


}
