package com.jy.restDemo.controller;

import com.jy.restDemo.dto.DataItem;
import com.jy.restDemo.dto.request.DataRequest;
import com.jy.restDemo.service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class DataController {

    private DataService dataService;

    public DataController(DataService dataService) {

        this.dataService = dataService;

    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<DataItem>> get(@RequestParam Map<String, String> params) {

        String queryParam = params.get("query");

        return dataService.retrieveData(queryParam);

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity post(@RequestBody DataRequest request) {

        return dataService.persistData(request);

    }
}
