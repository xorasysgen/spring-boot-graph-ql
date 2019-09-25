package api.graph.ql.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.graph.ql.model.Stock;
import api.graph.ql.repository.StockRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class StockDataFetcher implements DataFetcher<Stock> {

	@Autowired
	private StockRepository stockRepository;
	
	@Override
	public Stock get(DataFetchingEnvironment environment) {
		Integer id=environment.getArgument("id");
		System.out.println("Id :#########" + id);
			Optional<Stock> optional= stockRepository.findById(id);
			System.out.println("optional.get()" + optional.get());
			return optional.get();
	}

}
