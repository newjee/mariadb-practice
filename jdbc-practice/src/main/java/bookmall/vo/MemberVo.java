package bookmall.vo;

public class MemberVo {

    private String mbrName;
    private String mbrPhone;
    private String mbrEmail;
    private String mbrPwd;

    public String getMbrName() {
        return mbrName;
    }

    public void setMbrName(String mbrName) {
        this.mbrName = mbrName;
    }

    public String getMbrPhone() {
        return mbrPhone;
    }

    public void setMbrPhone(String mbrPhone) {
        this.mbrPhone = mbrPhone;
    }

    public String getMbrEmail() {
        return mbrEmail;
    }

    public void setMbrEmail(String mbrEmail) {
        this.mbrEmail = mbrEmail;
    }

    public String getMbrPwd() {
        return mbrPwd;
    }

    public void setMbrPwd(String mbrPwd) {
        this.mbrPwd = mbrPwd;
    }

    @Override
    public String toString() {
        return "MemberVo{" +
                "mbrName='" + mbrName + '\'' +
                ", mbrPhone='" + mbrPhone + '\'' +
                ", mbrEmail='" + mbrEmail + '\'' +
                ", mbrPwd='" + mbrPwd + '\'' +
                '}';
    }
}
