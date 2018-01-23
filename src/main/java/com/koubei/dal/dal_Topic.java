package com.koubei.dal;


import com.koubei.util.com_JdbcUtil;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public class dal_Topic {
	public static Integer kb_API_GetTopicIdByGuid(String guid) throws SQLException, ConfigurationException, ClassNotFoundException {
		String sql = "{CALL kb_API_GetTopicIdByGuid(?)}";
		CachedRowSet rowSet=com_JdbcUtil.execute(sql, new Object[] { guid });
		while (rowSet.next()){
			return rowSet.getInt(1);
		}
		return null;
	}
	public static List<CachedRowSet> kb_GetTopicByIds(String topicIds)
			throws SQLException, ConfigurationException, ClassNotFoundException {
		String sql = "{CALL kb_GetTopicByIds(?)}";
		return com_JdbcUtil.executeList(sql, new Object[] { topicIds });
	}

	public static List<CachedRowSet> kb_GetTopicById(Integer  id)
			throws SQLException, ConfigurationException, ClassNotFoundException {
		String sql = "{CALL kb_GetTopicById(?)}";
		return com_JdbcUtil.executeList(sql, new Object[] { id });
	}

	public static CachedRowSet kb_GetTopicBaseInfoById(int id)
			throws SQLException, ConfigurationException, ClassNotFoundException {
		String sql = "{CALL kb_GetTopicBaseInfoById(?)}";
		return com_JdbcUtil.execute(sql, new Object[] { id });
	}

	/**
	 * 获取口碑列表通过车型ID
	 * @param serialId
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */

	public static CachedRowSet kb_GetTopicListByCsId(int serialId, int pageSize)
			throws SQLException, ConfigurationException, ClassNotFoundException {
		String sql = "{CALL kb_GetTopicListByCsId(?,?)}";
		return com_JdbcUtil.execute(sql, new Object[] { pageSize, serialId });
	}
}
