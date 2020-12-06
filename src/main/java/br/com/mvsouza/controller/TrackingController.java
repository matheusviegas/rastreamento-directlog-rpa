package br.com.mvsouza.controller;

import br.com.mvsouza.dto.TrackingRequest;
import br.com.mvsouza.dto.TrackingResponse;
import br.com.mvsouza.scraping.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    @Autowired
    private ScrapingService scrapingService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<TrackingResponse>> trackPackage(@RequestBody TrackingRequest request) throws Exception {
        return ResponseEntity.ok(scrapingService.execute(request));
    }

}
