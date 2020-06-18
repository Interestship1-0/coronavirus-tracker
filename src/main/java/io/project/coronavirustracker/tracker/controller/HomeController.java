package io.project.coronavirustracker.tracker.controller;

import io.project.coronavirustracker.tracker.model.LocationStats;
import io.project.coronavirustracker.tracker.service.DataSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    DataSevice dataSevice;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = dataSevice.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(state-> state.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
