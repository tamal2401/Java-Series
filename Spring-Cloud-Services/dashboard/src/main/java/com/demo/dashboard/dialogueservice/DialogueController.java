package com.demo.dashboard.dialogueservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

@RestController
public class DialogueController {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
        log.info("\n result :: "+newInsult);
        return "index";
    }

    @GetMapping(value = "/get/quote")
    public String getNewQuote() throws UnresponsiveServiceException {
        StrategyBuilder quoteBuilder = new StrategyBuilderImpl(motivation);
        MotivationModel newQuote = (MotivationModel)quoteBuilder.execute();
        log.info("\n result :: "+newQuote);
        return "index";
    }
}
