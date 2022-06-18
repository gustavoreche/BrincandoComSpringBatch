package com.br.springbatch.tasklet;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.springbatch.model.Person;
import com.br.springbatch.repository.PersonRepository;
import com.br.springbatch.util.CSVUtil;

@Service
public class ReadFileTasklet implements Tasklet, StepExecutionListener {

	private final Logger logger = LoggerFactory.getLogger(ReadFileTasklet.class);

	private List<Person> persons;
	private CSVUtil file;
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.logger.info("Iniciando processo de leitura do arquivo.");
		this.persons = new ArrayList<>();
		this.file = new CSVUtil("fileTeste/arquivoTeste");
		this.logger.info("Arquivo a ser analisado: fileTeste/arquivoTeste");
	}

	@Override
	@Transactional
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		List<String[]> lines = this.file.readLines();
		lines.stream()
			.forEach(informations -> {
				logger.info("Read line: {}", informations);
				int index = 0;
				this.persons.add(new Person(informations[index++], 
						informations[index++],
						informations[index++]));
		});
		this.personRepository.deleteAllInBatch();
		this.personRepository.saveAll(this.persons);
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
//		stepExecution.getJobExecution().getExecutionContext().put("persons", this.persons);
		this.logger.info("Finalizado processo de leitura do arquivo.");
		return ExitStatus.COMPLETED;
	}

}
