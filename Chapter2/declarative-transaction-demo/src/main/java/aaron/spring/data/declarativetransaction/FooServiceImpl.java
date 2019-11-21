package aaron.spring.data.declarativetransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FooServiceImpl implements FooService {
    @Autowired
    private FooService fooService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into foo(bar) values ('aaa')");
    }

    @Override
    @Transactional(rollbackFor = RollBackException.class)
    public void insertThenRollback() throws RollBackException {
        jdbcTemplate.execute("insert into foo(bar) values('bbb')");
        throw new RollBackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollBackException {
        // Spring 的 AOP 本质上是为类做了一个代理。调用 fooService ，你以为是调用 fooserviceImpl，其实是调用
        // 一个增强后的代理类。
        // 问题解法：调用增强后的代理类的方法。
//        insertThenRollback();
        fooService.insertThenRollback(); // 内部调用
    }
}
