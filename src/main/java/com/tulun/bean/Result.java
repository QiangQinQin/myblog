package com.tulun.bean;

/**
 * @author QiangQin
 * * @date 2021/4/26
 */
/*
*  封装统一的结果集 给前端
 */
public class Result {
    //操作结果(类似于http返回200、400状态信息)
    private String resultCode;
    //错误信息
    private String errorInfo;
    //附属对象
    private  Object object;

    public String getResultCoed() {
        return resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }
    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Result(String resultCoed, String errorInfo) {
        this.resultCode = resultCoed;
        this.errorInfo = errorInfo;
//        System.out.println(errorInfo);
    }

    public Result(String resultCoed, String errorInfo, String object){
        super();
        this.resultCode=resultCoed;
        this.errorInfo=errorInfo;
        this.object=object;
        System.out.println(errorInfo);
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCoed='" + resultCode + '\'' +
                ", errorInfo='" + errorInfo + '\'' +
                ", object=" + object +
                '}';
    }
}
