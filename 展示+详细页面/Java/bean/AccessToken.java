package bean;

public class AccessToken {
    private String access_token;
    private String exprires_in;

    public AccessToken() {
    }

    public AccessToken(String access_token, String exprires_in) {
        this.access_token = access_token;
        this.exprires_in = exprires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExprires_in() {
        return exprires_in;
    }

    public void setExprires_in(String exprires_in) {
        this.exprires_in = exprires_in;
    }
}
