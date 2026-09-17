package console;

import model.UrlCheckSummary;
import model.UrlResult;
import service.UrlCheckerService;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpConnectTimeoutException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class UrlCheckerConsole {
    private final UrlCheckerService urlCheckerService;
    private final Scanner scanner;
    public UrlCheckerConsole(Scanner scanner, UrlCheckerService urlCheckerService){
        this.scanner = scanner;
        this.urlCheckerService = urlCheckerService;
    }

    public int getMenuOption(){
        System.out.println("Java URL Checker");
        System.out.println("=================");
        System.out.println("1. Check URL");
        System.out.println("2. Check Multiple URLs");
        System.out.println("3. Check URLs from File");
        System.out.println("4. Exit");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public void performOptionAction(int option){
        switch (option) {
            case 1:
                handleUrl();
                break;
            case 2:
                handleMultipleUrl();
                break;
            case 3:
                handleFileUrls();
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    public void handleUrl(){
        List<String> urlList = new ArrayList<>();
        System.out.println("Enter URL: ");
        String url = scanner.nextLine();
        urlList.add(url);
        checkAndDisplayResult(urlList);
    }

    public void handleMultipleUrl(){
        System.out.print("How many URLs do you want to check?");
        int urlCount = scanner.nextInt();
        scanner.nextLine();
        List<String> urlList = new ArrayList<>();
        if(urlCount>0) {
            for (int i = 0; i < urlCount; i++) {
                System.out.print("Enter URL " + (i + 1) + ": ");
                String urlString = scanner.nextLine();
                urlList.add(urlString);
            }
            checkAndDisplayResult(urlList);
        }else {
            System.out.println("URL count couldn't be negative or zero!");
        }
    }

    public void handleFileUrls(){
        List<String> urlList;
        System.out.println("Enter file path:");
        String filePathStr = scanner.nextLine();
        try {
            urlList = urlCheckerService.readFile(filePathStr);
            checkConcurrently(urlList);
        }catch (NoSuchFileException e){
            System.out.println("File not found.");
        }catch (IOException e){
            System.out.println("network/request problem");
        }
    }

    public void checkAndDisplayResult(List<String> urlList){
        UrlCheckSummary urlCheckSummary = new UrlCheckSummary();
        System.out.println("Checking URLs...");
        for (String url : urlList) {
            System.out.println("Result for: " + url);
            boolean urlValid = urlCheckerService.isValidUrl(url);
            if (urlValid) {
                try {
                    UrlResult urlResult = urlCheckerService.httpCheck(url);
                    System.out.println("Response Code: " + urlResult.getStatusCode());
                    System.out.println("Response Time: " + urlResult.getResponseTime() + " ms");
                    urlCheckSummary.recordResponse(url,urlResult.getResponseTime(),urlResult.getStatusCode());
                } catch (HttpConnectTimeoutException e) {
                    System.out.println("Request timed out.");
                    urlCheckSummary.recordFailedRequest();
                } catch (ConnectException e) {
                    System.out.println("Unable to connect to the server.");
                    urlCheckSummary.recordFailedRequest();
                } catch (IOException e) {
                    System.out.println("network/request problem");
                    urlCheckSummary.recordFailedRequest();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Request was interrupted");
                    urlCheckSummary.recordFailedRequest();
                }
            } else {
                System.out.println("✗ Invalid URL.");
                urlCheckSummary.recordInvalidUrl();
            }
        }
        urlCheckSummary.printSummary();
    }

    public void checkConcurrently(List<String> urlList) {
        UrlCheckSummary urlCheckSummary = new UrlCheckSummary();
        System.out.println("Checking URLs...");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<UrlResult>> futureList = new ArrayList<>();
        try {
            for (String url : urlList) {
                boolean validUrl = urlCheckerService.isValidUrl(url);
                if (validUrl) {
                    Callable<UrlResult> urlResultCallable = () -> urlCheckerService.httpCheck(url);
                    futureList.add(executorService.submit(urlResultCallable));
                } else {
                    urlCheckSummary.recordInvalidUrl();
                }
            }

            for (Future<UrlResult> result : futureList) {
                try {
                    UrlResult urlResult = result.get();
                    urlCheckSummary.recordResponse(urlResult.getUrl(), urlResult.getResponseTime(), urlResult.getStatusCode());
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof IOException) {
                        urlCheckSummary.recordFailedRequest();
                    } else {
                        System.out.println("Unexpected error: " + cause.getMessage());
                        urlCheckSummary.recordFailedRequest();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    urlCheckSummary.recordFailedRequest();
                }
            }
        }finally {
            executorService.shutdown();
        }

        urlCheckSummary.printSummary();
    }
}