package webshop;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saleProduct(long id, int amount) {
        if (productRepository.getStock(id) < amount) {
            throw new IllegalArgumentException("Can not be sold, stock level is low.");
        } else {
            productRepository.updateProductStock(id, amount);
        }
    }
}
