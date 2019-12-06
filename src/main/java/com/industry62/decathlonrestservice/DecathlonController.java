package com.industry62.decathlonrestservice;

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
    @PostMapping(path = "/calculatepoints")
    public DecathlonResultDto calculatePoints(@RequestBody List<EventResultDto> eventResults) {
        return decathlonService.calculatePoints(eventResults);
    }
}
