package fresher.thitracnghiem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/statistic")
public class StatisticController {

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public String getStatisticByTests(){
        return "admin/report/statistic-by-tests";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getStatisticByUsers(){
        return "admin/report/statistic-by-users";
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public String statisticByQuestions(){
        return "admin/report/statistic-by-questions";
    }

    @RequestMapping(value = "/test")
    public String statisticTestsByUsers(){
        return "admin/report/statistic-tests-by-users";
    }

    @RequestMapping(value = "/status")
    public String getAllStatusTests(){
        return "admin/report/all-status-test";
    }
}
