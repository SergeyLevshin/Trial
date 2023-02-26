package ru.levshin.trial.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levshin.trial.dao.AbstractDAO;
import ru.levshin.trial.dto.product.ProductDTO;
import ru.levshin.trial.dto.product.ProductInfo;
import ru.levshin.trial.model.Product;
import ru.levshin.trial.model.Score;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final AbstractDAO<Product> dao;

    @Autowired
    public void setClazz() {
        dao.setClazz(Product.class);
    }

    public Long create(Product product) {
        return dao.create(product).getId();
    }

    public List<ProductDTO> getAll() {
        return dao.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public ProductInfo getInfo(Long id) {
        return convertToInfo(getById(id));
    }

    public Product getById(Long id) {
        return dao.findById(id);
    }

    private ProductInfo convertToInfo(Product product) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setDescription(product.getDescription());
        productInfo.setAverageScore(getAverageScore(product.getScores()));
        productInfo.setScoreDistribution(getScoreDistribution(product.getScores()));
        productInfo.setCurrentScore(3);//todo
        return productInfo;
    }

    private Map<Integer, Long> getScoreDistribution(List<Score> scores) {
        return scores.stream().collect(Collectors.groupingBy(Score::getValue, Collectors.counting()));
    }

    private BigDecimal getAverageScore(List<Score> scores) {
        OptionalDouble average = scores.stream().mapToInt(Score::getValue).average();
        if (average.isPresent()) {
            return BigDecimal.valueOf(average.getAsDouble()).setScale(1, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
