package com.demo.crud;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.DataAccessException;
        import org.springframework.stereotype.Service;

        import javax.transaction.Transactional;
        import java.util.List;

@Service
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED,
                    rollbackOn = DataAccessException.class)
    public Product saveData(Product data) {
        return productRepository.save(data);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED,
            rollbackOn = DataAccessException.class)
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
