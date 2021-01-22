package stackoverflow.infrastructure.persistence.helper;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceProvider {

    static DataSourceProvider instance = new DataSourceProvider();

    private DataSource ds;
    private Connection con;

    private DataSourceProvider() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUrl("jdbc:mysql://localhost:3306/codemad");
        datasource.setUser("root");
        datasource.setPassword("amt2");

        ds = datasource;

        try {
            con = datasource.getConnection();
        } catch (SQLException throwables) { }

        instance = this;
    }

    public static DataSource getDataSource(){
        return instance.ds;
    }

    public static Connection getConnection(){
        return instance.con;
    }
}
