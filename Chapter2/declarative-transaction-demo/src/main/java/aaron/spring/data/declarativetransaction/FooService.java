package aaron.spring.data.declarativetransaction;

public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollBackException;
    void invokeInsertThenRollback() throws RollBackException;
}
