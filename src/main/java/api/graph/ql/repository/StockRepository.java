package api.graph.ql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.graph.ql.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

}
