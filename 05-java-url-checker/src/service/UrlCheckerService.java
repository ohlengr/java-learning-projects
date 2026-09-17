package service;

import model.UrlResult;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class UrlCheckerService {
    private final HttpClient httpClient;

    public UrlCheckerService(){
        httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
    }

    public boolean isValidUrl(String urlString){
        if(!urlString.isBlank()){
            try {
                URI uri = new URI(urlString);
                String scheme = uri.getScheme();
                if(scheme!=null && !scheme.isBlank() && (scheme.equals("http") || scheme.equals("https"))){
                    String host = uri.getHost();
                    if(host!=null && !host.isBlank()){
                        return true;
                    }
                }
            } catch (URISyntaxException e) {
                return false;
            }
        }
        return false;
    }

    public UrlResult httpCheck(String urlString) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(urlString)).GET().timeout(Duration.ofSeconds(5)).build();
        long start = System.nanoTime();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        long end = System.nanoTime();
        long elapsedNanos = end-start;
        return new UrlResult(response.uri().toString(), response.statusCode(), TimeUnit.NANOSECONDS.toMillis(elapsedNanos));
    }

    public List<String> readFile(String filePathStr) throws IOException {
        Path filePath = Path.of(filePathStr);
        if(Files.exists(filePath) && Files.isRegularFile(filePath)){
            List<String> lines = Files.readAllLines(filePath);
            return lines.stream().filter(line -> !line.isBlank()).collect(Collectors.toList());
        }else {
            throw new NoSuchFileException(filePathStr);
        }
    }
}