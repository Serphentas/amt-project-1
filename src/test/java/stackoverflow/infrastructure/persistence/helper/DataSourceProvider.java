package stackoverflow.infrastructure.persistence.helper;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSourceProvider {
    public static DataSource getDataSource(){
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUrl("jdbc:mysql://localhost:3306/codemad");
        datasource.setUser("root");
        datasource.setPassword("codemad");
        return datasource;
    }
}