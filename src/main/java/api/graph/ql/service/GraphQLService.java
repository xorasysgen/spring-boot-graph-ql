package api.graph.ql.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import api.graph.ql.model.Stock;
import api.graph.ql.repository.StockRepository;
import api.graph.ql.service.datafetcher.AllStockDataFetcher;
import api.graph.ql.service.datafetcher.StockDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Value("classpath:stock.graphql")
	Resource resource;
	
	@Autowired
	private StockRepository stockRepository;
	
	private GraphQL graphQL;
	
	@Autowired
	private AllStockDataFetcher allStockDataFetcher;
	
	@Autowired
	private StockDataFetcher stockDataFetcher;
	
	// load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        //Insert Temp records of Stock into Repository
        insertStocksIntoDataBase();
        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build(); // init graph
    }
    
    private RuntimeWiring buildRuntimeWiring() {
    	return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                		.dataFetcher("allStocks", allStockDataFetcher)
                        .dataFetcher("stock", stockDataFetcher))
                .build();
	}

	private void insertStocksIntoDataBase() {

        Stream.of(new Stock(1,"HdfcBank",1200,1250,1195,1247,1245),
        		new Stock(2,"Sbin",200,250,195,147,245),
        		new Stock(3,"Hdfc",1100,1150,1195,1147,1145),
        		new Stock(4,"Nifty",11590,11655,11539,11588,11580), 
        		new Stock(5,"Itc",255,256,260,245,254),
        		new Stock(6,"Lt",1540,1550,1595,1547,1540),
        		new Stock(7,"ioc",140,139,142,144,145)
        		
        ).forEach(stockRepository::save);
    }
	
	
	public GraphQL getGraphQL() {
        return graphQL;
    }
}
