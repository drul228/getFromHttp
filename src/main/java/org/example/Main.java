package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class Main {
    final static String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static void main(String[] args) {
        HttpGet request = new HttpGet(URL);
        ObjectMapper objectMapper = new ObjectMapper();
        try (CloseableHttpClient closeableHttpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.DEFAULT)
                .build();
             CloseableHttpResponse response = closeableHttpClient.execute(request);) {
            List<Facts> factsList = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Facts>>() {
            });
            factsList.stream()
                    .filter(x -> x.getUpvotes() != null)
                    .forEach(System.out::println);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}