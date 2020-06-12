package com.spring.batch;

import lombok.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.net.MalformedURLException;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

    @Value("${local.file.path}")
    private String csvPath;

    @Autowired
    ResourceLoader loader;

    @Autowired
    JobLauncher launcher;

    @Autowired
    ItemCountListener listener;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

    @Bean
    public String perform(Job job) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        launcher.run(job, params);
        return "success";
    }
    @Bean
    ItemReader<Sales> itemReader() throws MalformedURLException {
        return new FlatFileItemReaderBuilder<Sales>()
                .name("read_csv")
                .resource(new FileSystemResource("src/main/resources/csv/sales_data.csv"))
                .targetType(Sales.class)
                .delimited().delimiter(",").names("country,item_type,sales_channel,orderDate,unitSold,unitPrice,unitCost".split(","))
                .linesToSkip(1)
                .fieldSetMapper(fieldSet -> {
                    Sales s = new Sales();
                    s.setCountry(fieldSet.readString("country"));
                    s.setItem_type(fieldSet.readString("item_type"));
                    s.setSales_channel(fieldSet.readString("sales_channel"));
                    s.setOrderDate(fieldSet.readDate("orderDate"));
                    s.setUnitCost(fieldSet.readFloat("unitCost"));
                    s.setUnitPrice(fieldSet.readFloat("unitPrice"));
                    s.setUnitSold(fieldSet.readInt("unitSold"));
                    return s;
                })
                .build();
    }

    @Bean
    ItemWriter<Sales> itemWritter(DataSource ds) {
        return new JdbcBatchItemWriterBuilder<Sales>()
                .dataSource(ds)
                .sql("insert into SALES(country, item_type, sales_channel, orderDate, " +
                        "unitSold, unitPrice, unitCost) values (:country," +
                        " :item_type, :sales_channel, :orderDate, :unitSold, :unitPrice, :unitCost)")
                .beanMapped()
                .build();
    }

    @Bean
    Job createJob(JobBuilderFactory jbf,
                  StepBuilderFactory sbf,
                  ItemReader<? extends Sales> itemReader,
                  ItemWriter<? super Sales> itemWritter) {

        Step s1 = sbf.get("csv_to_db")
                .<Sales, Sales>chunk(100)
                .reader(itemReader)
                .writer(itemWritter)
                .listener(listener)
                .build();

        return jbf.get("SalesRecordProcessingJobpo")
                .incrementer(new RunIdIncrementer())
                .start(s1)
                .build();
    }
}



@Component
class ItemCountListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
    }

    @Override
    public void afterChunk(ChunkContext context) {

        int count = context.getStepContext().getStepExecution().getReadCount();
        System.out.println("ItemCount: " + count);
    }

    @Override
    public void afterChunkError(ChunkContext context) {
    }
}
