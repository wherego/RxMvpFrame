package com.yangzheandroid.rxmvp.beans;

/**
 * Author：Jalen on 2016/7/25 20:43
 * Contact: studylifetime@sina.com
 */
public class UserBean {
    /**
     * usersig : eJxlztFOgzAUBuB7nqLprca0Zcg08cJgg6wYYWwqu2kYdKUaoYGOsBnfXUeW2MRz*-3nnP-LAQDAVZxdFWXZ7hvDzUELCG4BRPDyD7VWFS8Md7vqH4pRq07wYmdEN6GH5zOE7IiqRGPUTp0DvkV99cGn85Pg0yIm2Md2RMkJn*g6iNKgZCtJa-QwD4M4dFlG9-SmTcT7cpMO8eIwFuTFxElx4S5lJB83ed1Lds0W6y5LhuZVY5Ju-XGbe-fhMaf1cy5aJt*iZnZnvTTqU5wL*Qh5xEV2oUF0vWqbKUAQ9vCvnwY6384PhcFaww__
     * retCode : 0
     * token : b7fb7727dd7d78b7835832079c7af453
     * expireDate : 1470280726470
     * retMsg :
     * accounttype : 6340
     * identifier : 7
     * sdkappid : 1400012171
     */
    //开播需要的四个字符串
    private String usersig;
    private String accounttype;
    private String identifier;
    private String sdkappid;

    private int retCode;
    private String token;
    private String expireDate;//到期的时期
    private String retMsg;

    public String getUsersig() {
        return usersig;
    }

    public void setUsersig(String usersig) {
        this.usersig = usersig;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSdkappid() {
        return sdkappid;
    }

    public void setSdkappid(String sdkappid) {
        this.sdkappid = sdkappid;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "usersig='" + usersig + '\'' +
                ", retCode=" + retCode +
                ", token='" + token + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", accounttype='" + accounttype + '\'' +
                ", identifier='" + identifier + '\'' +
                ", sdkappid='" + sdkappid + '\'' +
                '}';
    }
}
