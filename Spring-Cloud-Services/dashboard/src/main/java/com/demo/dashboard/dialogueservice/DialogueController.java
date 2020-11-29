package com.demo.dashboard.dialogueservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class DialogueController {

    @Autowired
    private MotivationalStrategyImpl motivation;
    @Autowired
    private InsultStrategyImpl insult;

    public DialogueController() {
    }

    @GetMapping(value = "/get/insult")
    public String getNewInsult(ModelAndView model) throws UnresponsiveServiceException {
        StrategyBuilder insultBuilder = new StrategyBuilderImpl(insult);
        InsultModel newInsult = (InsultModel)insultBuilder.execute();
        return "index";
    }

    @GetMapping(value = "/get/quote")
    public String getNewQuote() throws UnresponsiveServiceException {
        StrategyBuilder quoteBuilder = new StrategyBuilderImpl(motivation);
        MotivationModel newQuote = (MotivationModel)quoteBuilder.execute();
        return "index";
    }
}
