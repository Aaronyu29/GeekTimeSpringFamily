package aaron.spring.data.simplejdbcdemo;

import javafx.scene.chart.BarChart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData() {
        Arrays.asList("b", "c", "ddd").forEach(bar -> jdbcTemplate.update("insert into FOO(BAR) values(?)", bar));
        // 下面是使用 simpleJDBCINsert 的结果，首先要将 simpleJDBCInsert 加入容器中进行管理。
        HashMap<String, String> row = new HashMap<>();
        row.put("BAR", "xyz");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("ID of value " + id.longValue());
    }

    public void listData() {
        log.info("count {} " + jdbcTemplate.queryForObject("select count(*) from FOO", Long.class));
        List<String> list = jdbcTemplate.queryForList("select bar from FOO", String.class);
        list.forEach((String s) -> log.info("Bar {} " + s));
        // 下面是封装到对象中
        List<Foo> fooList = jdbcTemplate.query("select * from FOO", new RowMapper<Foo>() {
            public Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Foo.builder().id(rs.getLong(1)).Bar(rs.getString(2)).build();
            }
        });
        fooList.forEach(s -> log.info("FooObject {} " + s));
    }

}
