package model;

public class UrlCheckSummary {
    private int totalUrls;
    private int validUrls;
    private int invalidUrls;
    private int responseCount;
    private int successfulResponses;
    private int redirects;
    private int clientErrors;
    private int serverErrors;
    private int failedTimeoutUrls;
    private long totalResponseTime;
    private long averageResponseTime;
    private String fastestUrl;
    private long fastestResponseTime;
    private String slowestUrl;
    private long slowestResponseTime;

    public void recordResponse(String url, long responseTime, int statusCode){
        totalUrls++;
        validUrls++;

        if(200<=statusCode && statusCode<=299){
            successfulResponses++;
        }else if (300<=statusCode && statusCode<=399){
            redirects++;
        } else if (400<=statusCode && statusCode<=499) {
            clientErrors++;
        } else if (500<=statusCode && statusCode<=599) {
            serverErrors++;
        }

        responseCount++;
        totalResponseTime += responseTime;
        averageResponseTime = totalResponseTime / responseCount;
        if (fastestResponseTime == 0) {
            fastestResponseTime = responseTime;
            fastestUrl = url;
        } else if (responseTime < fastestResponseTime) {
            fastestResponseTime = responseTime;
            fastestUrl = url;
        }

        if (slowestResponseTime == 0) {
            slowestUrl = url;
            slowestResponseTime = responseTime;
        } else if (responseTime > slowestResponseTime) {
            slowestResponseTime = responseTime;
            slowestUrl = url;
        }
    }

    public void recordFailedRequest(){
        totalUrls++;
        failedTimeoutUrls++;
    }

    public void recordInvalidUrl(){
        totalUrls++;
        invalidUrls++;
    }

    public void printSummary(){
        System.out.println("========== URL CHECK SUMMARY ==========");
        System.out.println("Total URLs: " + totalUrls);
        System.out.println("Valid URLs: " + validUrls);
        System.out.println("Invalid URLs: " + invalidUrls);
        System.out.println("Successful: " + successfulResponses);
        System.out.println("Redirects: " + redirects);
        System.out.println("Client Errors: " + clientErrors);
        System.out.println("Server Errors: " + serverErrors);
        System.out.println("Failed/Timeout: " + failedTimeoutUrls);
        System.out.println("Average Time: " + ((responseCount>0)?averageResponseTime + " ms":"N/A"));
        System.out.println("Fastest URL: " + ((responseCount>0)?fastestUrl + "(" + fastestResponseTime + " ms)":"N/A"));
        System.out.println("Slowest URL: " + ((responseCount>0)?slowestUrl + "(" + slowestResponseTime + " ms)":"N/A"));
        System.out.println("========================================");
    }
}
