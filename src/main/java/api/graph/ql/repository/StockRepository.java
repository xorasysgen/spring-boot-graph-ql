package api.graph.ql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import api.graph.ql.model.Stock;

public interface StockRepository extends RevisionRepository<Stock, Integer, Integer> , JpaRepository<Stock, Integer> {

}
