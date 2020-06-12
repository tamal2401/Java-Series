package com.spring.batch;

import lombok.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

    @Value("${local.file.path}")
    private String csvPath;

    @Autowired
    ResourceLoader loader;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }

    @Bean
    ItemReader<Sales> itemReader() {
        return new FlatFileItemReaderBuilder<Sales>()
                .name("read_csv")
                .resource(loader.getResource("classpath:"+csvPath))
                .targetType(Sales.class)
                .delimited().delimiter(",").names("country,item_type,sales_channel,orderDate,unitSold,unitPrice,unitCost".split(","))
                .build();
    }

    @Bean
    ItemWriter<Sales> itemWritter(DataSource ds) {
//        return new JdbcBatchItemWriterBuilder<Sales>()
//                .dataSource(ds)
//                .sql("insert into SALES(country, item_type, sales_channel, orderDate, " +
//                        "unitSold, unitPrice, unitCost) values (insert into SALES( :country," +
//                        " :item_type, :sales_channel, :orderDate, :unitSold, :unitPrice, :unitCost)")
//                .columnMapped()
//                .build();
        return (items)->{
            System.out.println("OOpppsss!!!!");
            items.stream()
                    .forEach(each-> System.out.println(each));
        };
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
                .build();

        return jbf.get("SalesRecordProcessingJobpo")
                .incrementer(new RunIdIncrementer())
                .start(s1)
                .build();
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
class Sales {
    private String country;
    private String item_type;
    private String sales_channel;
    private String orderDate;
    private int unitSold;
    private float unitPrice;
    private float unitCost;
}
