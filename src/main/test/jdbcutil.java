import com.koubei.util.com_JdbcUtil;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public class jdbcutil {
    @Test
    public void Test() throws SQLException, ConfigurationException, ClassNotFoundException {
        String sql = "{call aaa_test(?,?,?)}";
        Object params[] = {2608, 10, 0};
        List<CachedRowSet> list = com_JdbcUtil.executeList(sql, params);
        System.out.println(params[params.length - 1]);
    }
}
