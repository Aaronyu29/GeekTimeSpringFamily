package aaron.spring.data.simplejdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BatchFooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsert() {
        jdbcTemplate.batchUpdate("INSERT into FOO(BAR) values (?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                //@param parameterIndex the first parameter is 1, the second is 2, ...
                ps.setString(1,"b-"+i);
            }

            @Override
            public int getBatchSize() {
                // Return the size of the batch.
                return 2;
            }
        });

        // 还可以封装到一个 list 中来进行插入。
        List<Foo> list = new ArrayList<>();
        list.add(Foo.builder().id(100L).Bar("100-hh").build());
        list.add(Foo.builder().id(101L).Bar("101-shit").build());
        namedParameterJdbcTemplate.batchUpdate("insert into FOO values(:id,:bar)",
                SqlParameterSourceUtils.createBatch(list));
    }

}
