package com.industry62.decathlon.controller;

import com.industry62.decathlon.dto.DecathlonResultDto;
import com.industry62.decathlon.dto.EventResultDto;
import com.industry62.decathlon.dto.SportDto;
import com.industry62.decathlon.service.DecathlonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DecathlonController {

    @Autowired
    private DecathlonService decathlonService;

    @CrossOrigin
    @GetMapping("/sport")
    public Collection<SportDto> getSports() {
        return decathlonService.getSports();
    }

    @CrossOrigin
    @GetMapping("/results")
    public Collection<DecathlonResultDto> getResults() {
        return decathlonService.getResults();
    }

    @CrossOrigin
    @PostMapping(path = "/calculatepoints")
    public DecathlonResultDto calculatePoints(@RequestBody List<EventResultDto> eventResults) {
        return decathlonService.storeAndCalculatePoints(eventResults);
    }
}
