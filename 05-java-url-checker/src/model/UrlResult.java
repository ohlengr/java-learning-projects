package model;

public class UrlResult {

    private final String url;
    private final int statusCode;
    private final long responseTime;

    public UrlResult(String url, int statusCode, long responseTime){
        this.url = url;
        this.statusCode = statusCode;
        this.responseTime = responseTime;
    }

    public String getUrl(){
        return url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getResponseTime() {
        return responseTime;
    }
}
