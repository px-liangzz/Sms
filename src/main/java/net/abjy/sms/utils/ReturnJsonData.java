package net.abjy.sms.utils;

public class ReturnJsonData {
    //状态码，0表示成功，1表示处理中，-1表示失败
    private Integer code;
    //业务数据
    private Object data;
    //信息描述
    private String msg;



    public ReturnJsonData(){

    }
    public ReturnJsonData(Integer code,Object data,String msg){
        this.code=code;
        this.data=data;
        this.msg=msg;
    }
    //成功，不返回数据
    public static ReturnJsonData buildSucess(){
        return new ReturnJsonData(0,null,null);
    }

    //成功，返回数据
    public static ReturnJsonData buildSucess(String msg){
        return new ReturnJsonData(0,null,msg);
    }

    //成功，返回数据
    public static ReturnJsonData buildSucess(Object data){
        return new ReturnJsonData(0,data,null);
    }

    public static ReturnJsonData buildSucess(String msg,Object data){
        return new ReturnJsonData(0,data,msg);
    }

    //失败，返回错误信息
    public static ReturnJsonData buildError(String msg){
        return new ReturnJsonData(-1,null,msg);
    }
    //失败，返回自定义状态码以及错误信息

    public static ReturnJsonData buildError(Integer code,String msg){
        return new ReturnJsonData(code,null,msg);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
