package com.demo.dashboard.dialogueservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    public String getNewInsult(Model model) throws UnresponsiveServiceException {
        StrategyBuilder insultBuilder = new StrategyBuilderImpl(insult);
        List<CommonMessageModel> newInsultCollection = getCommonMsg(insultBuilder);
        log.info("\n result :: " + newInsultCollection);
        model.addAttribute("msgObj", newInsultCollection);
        return "index";
    }

    @GetMapping(value = "/get/quote")
    public String getNewQuote(Model model) throws UnresponsiveServiceException {
        StrategyBuilder quoteBuilder = new StrategyBuilderImpl(motivation);
        List<CommonMessageModel> newQuotesCollection = getCommonMsg(quoteBuilder);
        log.info("\n result :: " + newQuotesCollection);
        model.addAttribute("msgObj", newQuotesCollection);
        return "index";
    }

    private List<CommonMessageModel> getCommonMsg(StrategyBuilder builder) {

        List<CommonMessageModel> msgList = IntStream.range(1,7).mapToObj(each -> {
            CommonMessageModel msgType = null;
            try {
                msgType = builder.execute();
            } catch (UnresponsiveServiceException e) {
                e.printStackTrace();
            }
            return msgType;
        }).collect(Collectors.toList());
        return msgList;
    }
}
