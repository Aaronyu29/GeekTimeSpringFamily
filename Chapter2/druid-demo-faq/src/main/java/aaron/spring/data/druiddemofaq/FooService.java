package aaron.spring.data.druiddemofaq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void selectForUpdate() {
        // for update 是在事务里锁住记录。
        jdbcTemplate.queryForObject("select id from foo where id = 1 for update", Long.class);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
    }
}
