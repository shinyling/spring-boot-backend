package com.shiny.batch.config;

import com.shiny.batch.model.RecordReader;
import com.shiny.batch.model.RecordWriter;
import com.shiny.batch.processor.RecordProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.ResultSet;

/**
 * @author shiny
 **/
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private static final Logger logger= LogManager.getLogger(BatchConfiguration.class);

    @Bean
    public ItemReader<RecordReader> reader(@Qualifier("dataSource")DataSource dataSource){
        JdbcCursorItemReader<RecordReader> reader=new JdbcCursorItemReader<>();
        reader.setSql("select id,name,description from reader");
        reader.setDataSource(dataSource);
        reader.setRowMapper((ResultSet resultSet,int rowNum)->{
            if(!(resultSet.isAfterLast())&&!(resultSet.isBeforeFirst())){
                RecordReader recordReader=new RecordReader();
                recordReader.setId(resultSet.getString("id"));
                recordReader.setName(resultSet.getString("name"));
                recordReader.setDescription(resultSet.getString("description"));
                logger.info("RowMapper record :{}",recordReader);
                return recordReader;
            }else {
                logger.info("Returning null from rowMapper");
                return null;
            }
        });
        return reader;
    }

    @Bean
    public ItemWriter<RecordWriter> writer(@Qualifier("dataSource") DataSource dataSource, ItemPreparedStatementSetter<RecordWriter> statementSetter){
        JdbcBatchItemWriter<RecordWriter> writer=new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setItemPreparedStatementSetter(statementSetter);
        writer.setSql("insert into writer(id,name) values(?,?)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public ItemProcessor<RecordReader, RecordWriter> processor() {
        return new RecordProcessor();
    }

    @Bean
    public ItemPreparedStatementSetter<RecordWriter> statementSetter(){
        return (item,ps)->{
          ps.setString(1,item.getId());
          ps.setString(2,item.getName());
        };
    }


    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, Step s1, JobExecutionListener listener){
        RunIdIncrementer runIdIncrementer=new RunIdIncrementer();
        runIdIncrementer.setKey(String.valueOf(System.currentTimeMillis()));
        return jobBuilderFactory.get("job")
                .incrementer(runIdIncrementer)
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    protected Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<RecordReader> reader
            , ItemWriter<RecordWriter> writer, ItemProcessor<RecordReader,RecordWriter> processor){
        return stepBuilderFactory.get("step1")
                .<RecordReader,RecordWriter>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
