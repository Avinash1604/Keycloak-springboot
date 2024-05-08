package com.dunkware.keycloak;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Login service - calling kecloak
 */
@Service
public class LoginService {
    String tokenUrl = "http://localhost:8080/realms/master/protocol/openid-connect/token";

    public Map login(User formData) {
        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type","password");
        data.add("client_id","dunkware-client-id");
        data.add("username", formData.getUsername());
        data.add("password", formData.getPassword());

        // Build the request entity with headers and parameters
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(data, headers);

        // Send the POST request and retrieve the response
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                entity,
                Map.class);

        // Print the response body
        System.out.println("Response: " + responseEntity.getBody());
        return responseEntity.getBody();
    }
}
