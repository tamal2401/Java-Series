package com.webscraper.java.Spring.Mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.apache.poi.xwpf.converter.core.IXWPFConverter;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringMongoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoApplication.class, args);
    }

    @Autowired
    private GridFsTemplate template;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String inputFile="Resume.docx";
        String outputFile="Resume.pdf";

        System.out.println("inputFile:" + inputFile + ",outputFile:"+ outputFile);
        FileInputStream in=new FileInputStream(inputFile);
        XWPFDocument document=new XWPFDocument(in);
        File outFile=new File(outputFile);
        OutputStream out=new FileOutputStream(outFile);
        PdfOptions options=PdfOptions.create();
        IXWPFConverter<PdfOptions> ins = PdfConverter.getInstance();
        ins.convert(document,out,options);

        // storing file to MongoDb
        InputStream inputStream = new FileInputStream(outFile);
        template.store(inputStream, outFile.getName(), "application/pdf");
        System.out.println("file saved");

        System.exit(0);
    }
}
