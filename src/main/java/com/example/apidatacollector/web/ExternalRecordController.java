package com.example.apidatacollector.web;

import com.example.apidatacollector.domain.ExternalRecord;
import com.example.apidatacollector.service.ExternalRecordService;
import com.example.apidatacollector.web.dto.ExternalRecordResponse;
import com.example.apidatacollector.web.dto.FetchRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class ExternalRecordController {

    private final ExternalRecordService externalRecordService;

    public ExternalRecordController(ExternalRecordService externalRecordService) {
        this.externalRecordService = externalRecordService;
    }

    @PostMapping("/fetch")
    public ResponseEntity<ExternalRecordResponse> fetchAndStore(@Valid @RequestBody FetchRequest request) {
        ExternalRecord record = externalRecordService.fetchAndStore(
                request.getSource(),
                request.getRequest()
        );
        return ResponseEntity.ok(new ExternalRecordResponse(record));
    }

    @GetMapping
    public ResponseEntity<List<ExternalRecordResponse>> findAll() {
        List<ExternalRecordResponse> responses = externalRecordService.findAll()
                .stream()
                .map(ExternalRecordResponse::new)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
