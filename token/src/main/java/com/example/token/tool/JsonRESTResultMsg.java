package com.example.token.tool;

/**
 * #
 * # 　　　┏┓　　　┏┓
 * # 　　┏┛┻━━━┛┻┓
 * # 　　┃　　　　　　　 ┃
 * # 　　┃　　　━　　　 ┃
 * # 　　┃　┳┛　┗┳　┃
 * # 　　┃　　　　　　　 ┃
 * # 　　┃　　　┻　　　 ┃
 * # 　　┃　　　　　　　 ┃
 * # 　　┗━┓　　　┏━┛Codes are far away from bugs with the animal protecting
 * # 　　　　┃　　　┃    神兽保佑,代码无bug
 * # 　　　　┃　　　┃
 * # 　　　　┃　　　┗━━━┓
 * # 　　　　┃　　　　　 ┣┓
 * # 　　　　┃　　　　 ┏┛
 * # 　　　　┗┓┓┏━┳┓┏┛
 * # 　　　　　┃┫┫　┃┫┫
 * # 　　　　　┗┻┛　┗┻┛
 */
public enum JsonRESTResultMsg {
    SPEC_GROUP_CREATE_FAILED(500, "新增规格组失败"),
    SPEC_GROUP_NOT_FOUND(204, "规格组查询失败"),
    DELETE_SPEC_GROUP_FAILED(500, "商品规格组删除失败"),
    UPDATE_SPEC_GROUP_FAILED(500, "商品规格组更新失败"),
    ;
    int value;
    String message;
    private JsonRESTResultMsg(int value,String message){
        this.value=value;
        this.message=message;
    }
    public int value() {
        return this.value;
    }

    public String message() {
        return this.message;
    }
}
