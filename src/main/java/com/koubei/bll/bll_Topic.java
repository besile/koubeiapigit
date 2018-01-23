package com.koubei.bll;

import com.koubei.annotation.Cache;
import com.koubei.dal.dal_Topic;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class bll_Topic {
	/**
	 *
	 * @param topicIds
	 * @return
	 */
	@Cache("CacheTopicList")
	public static List<Integer> kb_GetTopicBaseInfoByIds(List<Integer> topicIds) {
		return topicIds;
	}
	@Cache("CacheTopic")
	public static Integer kb_API_GetTopicIdByGuid(String guid){
		try {
			return dal_Topic.kb_API_GetTopicIdByGuid(guid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**获取口碑列表通过车型ID
	 * @param serialId
	 * @param pageSize
	 * @return
	 */
	@Cache("CacheTopicList")
	public static List<Integer> kb_GetTopicListByCsId(Integer serialId, Integer pageSize) {
		List<Integer> list=new ArrayList<Integer>();
		try {
			CachedRowSet rs= dal_Topic.kb_GetTopicListByCsId(serialId, pageSize);
			while(rs.next()){
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO 日志记录
			e.printStackTrace();
		}
		return list;
	}
}
