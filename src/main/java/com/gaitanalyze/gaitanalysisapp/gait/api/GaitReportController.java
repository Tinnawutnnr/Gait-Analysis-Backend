package com.gaitanalyze.gaitanalysisapp.gait.api;

import com.gaitanalyze.gaitanalysisapp.gait.domain.GaitReport;
import com.gaitanalyze.gaitanalysisapp.gait.application.GaitReportQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gait")
public class GaitReportController {

    private final GaitReportQueryService queryService;

    public GaitReportController(GaitReportQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/sessions/{sessionId}/reports")
    public List<GaitReport> getReportBySession(@PathVariable Long sessionId) {
        return queryService.getReportsBySessionId(sessionId);
    }

}