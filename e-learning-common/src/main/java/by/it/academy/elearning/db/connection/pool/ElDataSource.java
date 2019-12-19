package by.it.academy.elearning.db.connection.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public final class ElDataSource {

    private static DataSource dataSource;

    private ElDataSource() {
    }

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/my_test?useUnicode=true&characterEncoding=UTF-8");
        config.setUsername("root");
        config.setPassword("pass");
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        dataSource = new HikariDataSource(config);
    }

    public static DataSource getInstance() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }



/*    public synchronized static void init(File file) throws FileNotFoundException {
        if (dataSource == null) {
            if (!file.isFile() || !file.exists()) {
                throw new FileNotFoundException("File not found " + file);
            }
            HikariConfig config = new HikariConfig(file.getAbsolutePath());
            dataSource = new HikariDataSource(config);
        }
    }*/

    public static DataSource getDataSource() throws IOException {
        if (dataSource == null) {
            throw new IOException("Datasource is null need to call init() first");
        }
        return dataSource;
    }

}
