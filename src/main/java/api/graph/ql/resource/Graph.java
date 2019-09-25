package api.graph.ql.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.graph.ql.service.GraphQLService;
import graphql.ExecutionResult;

@RestController
@RequestMapping("/graph")
public class Graph {

	@Autowired
	private GraphQLService graphQLService;
	
	@GetMapping("/")
	public List<String> entryPoint(){
		List<String> list=new ArrayList<String>();
		list.add("Graph QL");
		list.add("Postgres");
		list.add("Spring Boot");
		list.add("Hibernate");
		return list;
		
	}
	
	
	@PostMapping("/stock")
	public ResponseEntity<Object> getStocks(@RequestBody String query){
		ExecutionResult result= graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	
}
