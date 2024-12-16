package br.com.felipe.products_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<HashMap<String, String>> getApiStatus() {
        var response = new HashMap<String, String>();
        response.put("service", "Products-Api");
        response.put("status", "up");
        response.put("httpStatus", "200");
        return ResponseEntity.ok(response);
    }
}
