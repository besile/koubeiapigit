package com.koubei.cache.bll;

import com.koubei.dal.dal_Topic;
import com.koubei.model.*;
import com.koubei.util.com_ModelFillHelper;

import javax.sql.rowset.CachedRowSet;
import java.util.List;
import java.util.Map;

public class srv_Topic {
    public static m_D_Topic kb_GetTopicById(Integer topicId) throws Exception {
        List<CachedRowSet> list = dal_Topic.kb_GetTopicById(topicId);
        if (list == null || list.size() == 0) {
            return null;
        }
        m_D_Topic topic = com_ModelFillHelper.FillModel(list.get(0),
                m_D_Topic.class);
        if (topic == null) {
            return null;
        }
        m_D_Topic_Comment comment = com_ModelFillHelper.FillModel(list.get(1),
                m_D_Topic_Comment.class);
        topic.setComment(comment);
        List<m_D_Topic_Detail> details = com_ModelFillHelper.FillModelList(
                list.get(2), m_D_Topic_Detail.class);
        topic.setDetailList(details);
        List<m_D_Topic_Image> images = com_ModelFillHelper.FillModelList(
                list.get(3), m_D_Topic_Image.class);
        topic.setImageList(images);
        List<m_D_TopicWords> words = com_ModelFillHelper.FillModelList(
                list.get(4), m_D_TopicWords.class);
        topic.setTopicWordsList(words);
        return topic;
    }

    public static List<m_D_Topic> kb_GetTopicByIds(String topicIds)
            throws Exception {
        List<CachedRowSet> list = dal_Topic.kb_GetTopicByIds(topicIds);
        if (list == null || list.size() == 0) {
            return null;
        }
        List<m_D_Topic> topics = com_ModelFillHelper.FillModelList(list.get(0),
                m_D_Topic.class);
        if (topics == null || topics.size() == 0) {
            return null;
        }

        Map<String, List<m_D_Topic_Comment>> comments = com_ModelFillHelper
                .FillModelMapList(list.get(1), m_D_Topic_Comment.class);
        Map<String, List<m_D_Topic_Detail>> details = com_ModelFillHelper
                .FillModelMapList(list.get(2), m_D_Topic_Detail.class);
        Map<String, List<m_D_Topic_Image>> images = com_ModelFillHelper
                .FillModelMapList(list.get(3), m_D_Topic_Image.class);
        Map<String, List<m_D_TopicWords>> words = com_ModelFillHelper
                .FillModelMapList(list.get(4), m_D_TopicWords.class);
        for (m_D_Topic topic : topics) {
            int topicId = topic.getTopicId();
            if (comments != null && comments.containsKey(topicId)) {
                topic.setComment(comments.get(topicId).get(0));
            }
            if (details != null && details.containsKey(topicId)) {
                topic.setDetailList(details.get(topicId));
            }
            if (images != null && images.containsKey(topicId)) {
                topic.setImageList(images.get(topicId));
            }
            if (words != null && words.containsKey(topicId)) {
                topic.setTopicWordsList(words.get(topicId));
            }
        }
        return topics;
    }
}
