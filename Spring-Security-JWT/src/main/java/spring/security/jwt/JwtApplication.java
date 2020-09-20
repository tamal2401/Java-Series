package spring.security.jwt;

import com.sun.el.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}

@RestController
class MainController{

	public final static List<Student> stuArr = Arrays.asList(
			new Student(1, "Tamal"),
			new Student(2, "Susanta"),
			new Student(3, "Sagnik")
	);

	@GetMapping("/api/student/{studentId}")
	public Student getStuden(@PathVariable("studentId")Integer id){
		return stuArr.stream()
				.filter(each -> each.getId().equals(id))
				.findFirst()
				.orElseThrow(()->new IllegalStateException(String.format("Student {id} does not exist", id)));
	}

	@GetMapping("/api/v1/index")
	public String indec(){
		return "<h1>Welcome in spring boot</h1>";
	}
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student{
	private Integer id;
	private String name;
}