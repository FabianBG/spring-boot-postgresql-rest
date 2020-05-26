package com.fabianbg.app.transport.http;

import java.net.http.HttpResponse;
import java.util.List;

import com.fabianbg.domain.dto.DeveloperUpdateDTO;
import com.fabianbg.domain.model.Developer;
import com.fabianbg.domain.service.IDeveloperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("developers")
public class DeveloperController {

    private IDeveloperService developerService;

    @Autowired
    public DeveloperController(final IDeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping()
    public ResponseEntity<List<Developer>> getAllEndpoint() {
        return ResponseEntity.ok(this.developerService.getAll());
    }

    @PostMapping()
    public ResponseEntity<Developer> createEndpoint(@RequestBody Developer data) {
        return ResponseEntity.ok(this.developerService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateEndpoint(@PathVariable("id") String id,
            @RequestBody DeveloperUpdateDTO data) {
        return ResponseEntity.ok(this.developerService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEndpoint(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.developerService.delete(id));
    }

}