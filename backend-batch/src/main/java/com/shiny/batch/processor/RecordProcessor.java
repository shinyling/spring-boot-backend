package com.shiny.batch.processor;

import com.shiny.batch.model.RecordReader;
import com.shiny.batch.model.RecordWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author shiny
 **/
public class RecordProcessor implements ItemProcessor<RecordReader,RecordWriter> {

    private static final Logger logger= LogManager.getLogger(RecordProcessor.class);

    @Override
    public RecordWriter process(RecordReader recordReader) throws Exception {
        logger.info("Processing Record :{}",recordReader);
        RecordWriter recordWriter=new RecordWriter();
        recordWriter.setId(recordReader.getId());
        recordWriter.setName(recordReader.getName()+recordReader.getDescription());
        logger.info("Processed Writer :{}",recordWriter);
        return recordWriter;
    }

}
