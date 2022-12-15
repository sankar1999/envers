package com.envers;

import com.envers.entity.Book;
import com.envers.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@EnableJpaRepositories(repositoryFactoryBeanClass
		= EnversRevisionRepositoryFactoryBean.class)
public class EnversApplication {


	@Autowired
	private BookRepository repository;

	@PostMapping("/saveBook")
	public Book saveBook(@RequestBody Book book) {
		return repository.save(book);
	}

	@PutMapping("/updateBook")
	public String updateBook(@RequestParam int id, @RequestParam int pages) {
		Book book = repository.findById(id).get();
		book.setPages(pages);
		repository.save(book);
		return "Book Updated...";
	}

	@DeleteMapping("/deleteBook")
	public String deleteBook(@RequestParam int id) {
		repository.deleteById(id);
		return "Book Deleted...";
	}

	@GetMapping("/getinfo")
	public void getInfo(@RequestParam int id) {
		System.out.printf(String.valueOf(repository.findLastChangeRevision(id)));
	}

	public static void main(String[] args) {
		SpringApplication.run(EnversApplication.class, args);
	}

}
