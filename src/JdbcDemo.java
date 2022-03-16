import java.sql.*;

public class JdbcDemo {

    private static Connection dbConnection;

    public static void main(String[] args) {
        initConnection();
        try{
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ap","root","qJiw03K2zwJD");

            PreparedStatement psts = dbConnection.prepareStatement("SELECT * from product");
            ResultSet resultSet = psts.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
            }

        }catch(SQLException e){
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

        System.out.println("update...");
        updateRow();

        System.out.println("insert...");
        insertRow();

        System.out.println("delete...");
        deleteRow();
    }

    public static void initConnection() {
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop", "root", "qJiw03K2zwJD");

        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }

    }

    public static void getAll(){

    }

    public static void insertRow(){
        String createStatement = "INSERT INTO product(name, price) VALUES (?, ?)";
        try {


            PreparedStatement createProduct = dbConnection.prepareStatement(createStatement);
            //createProduct.setInt(1, 5);
            createProduct.setString(1, "Ost");
            createProduct.setInt(2, 47);
            createProduct.execute();
        }
        catch(SQLException e){
            System.out.println("Could not insert row");
            e.printStackTrace();
        }

    }

    public static void updateRow() {
        String updateStatement = "UPDATE product SET name = ?, price = ? WHERE id = ?";

        try {
            PreparedStatement updateProduct = dbConnection.prepareStatement(updateStatement);
            int id = 2;
            String name = "Kiks";
            int price = 11;
            updateProduct.setString(1, name);
            updateProduct.setInt(2, price);
            updateProduct.setInt(3, id);
            updateProduct.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Cannot update database");
            e.printStackTrace();
        }
    }

        public static void deleteRow(){
            String deleteStatement = "DELETE FROM product WHERE id = ?";
            int id = 4;
            try {
                PreparedStatement deleteProduct = dbConnection.prepareStatement(deleteStatement);
                deleteProduct.setInt(1, id);
                deleteProduct.execute();

            }
            catch (SQLException e){
                System.out.println("Could not delete id=" + id);
                e.printStackTrace();
            }



        }




}
