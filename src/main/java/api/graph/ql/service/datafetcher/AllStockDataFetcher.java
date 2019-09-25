package api.graph.ql.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import api.graph.ql.model.Stock;
import api.graph.ql.repository.StockRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllStockDataFetcher implements DataFetcher<List<Stock>> {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Override
	public List<Stock> get(DataFetchingEnvironment environment) {
		return stockRepository.findAll();
	}

}
