package webshop;

import javax.sql.DataSource;
import java.sql.*;

public class ProductRepository {

    private DataSource dataSource;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long insertProduct(String name, long price, long stock) {
        try (
                Connection conn = dataSource.getConnection();
                //language=sql
                PreparedStatement ps = conn.prepareStatement("insert into products(product_name, price, stock) values(?,?,?);", Statement.RETURN_GENERATED_KEYS)
        ) {
            setLine(name, price, stock, ps);
            ps.executeUpdate();
            return retrieveKey(ps);
        }
        catch (SQLException sqlException) {
            throw new IllegalStateException("Can not insert.", sqlException);
        }
    }

    private long retrieveKey(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new IllegalStateException("Can not insert values.");
        } catch (IllegalStateException ise) {
            throw ise;
        }
    }

    private void setLine(String name, long price, long stock, PreparedStatement ps) throws SQLException {
        ps.setString(1, name);
        ps.setLong(2, price);
        ps.setLong(3, stock);
    }

    public Product findProductById(long id) {
        try (
                Connection conn = dataSource.getConnection();
                //language=sql;
                PreparedStatement ps = conn.prepareStatement("select * from products where id = ?;")
        ) {
            ps.setLong(1, id);
            return retrieveProduct(ps);
        }
        catch (SQLException sqlException) {
            throw new IllegalStateException("Can not query based on ID", sqlException);
        }
    }

    private Product retrieveProduct(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Product(rs.getLong("id"), rs.getString("product_name"), rs.getLong("price"), rs.getInt("stock"));
            }
            throw new IllegalStateException("Can not find by ID");
        }
    }

    public void updateProductStock(long id, int stock) {
        try (
                Connection conn = dataSource.getConnection();
                //language=sql
                PreparedStatement ps = conn.prepareStatement("update products set stock = ? where id = ?;")
        ) {
            setLinesForProduct(id, stock, ps);
            ps.executeUpdate();
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Can not update by ID.", sqlException);
        }
    }

    private void setLinesForProduct(long id, int stock, PreparedStatement ps) throws SQLException {
        ps.setInt(1, getStock(id) - stock);
        ps.setLong(2, id);
    }

    public int getStock(long id) {
        try (
                Connection conn = dataSource.getConnection();
                //language=sql
                PreparedStatement ps = conn.prepareStatement("select stock from products where id = ?;")
        ) {
            ps.setLong(1, id);
            return retrieveStockValue(ps);
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Can not retrieve stock by id", sqlException);
        }
    }

    private int retrieveStockValue(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("stock");
            }
            throw new IllegalStateException("Can not query.");
        }
    }
}
