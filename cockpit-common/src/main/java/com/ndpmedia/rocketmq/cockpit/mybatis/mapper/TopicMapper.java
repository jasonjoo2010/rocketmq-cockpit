package com.ndpmedia.rocketmq.cockpit.mybatis.mapper;

import com.ndpmedia.rocketmq.cockpit.model.Chair;
import com.ndpmedia.rocketmq.cockpit.model.DataCenter;
import com.ndpmedia.rocketmq.cockpit.model.Status;
import com.ndpmedia.rocketmq.cockpit.model.Topic;
import com.ndpmedia.rocketmq.cockpit.model.TopicAvailability;
import com.ndpmedia.rocketmq.cockpit.model.TopicBrokerInfo;
import com.ndpmedia.rocketmq.cockpit.model.TopicMetadata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {

    long insert(TopicMetadata topicMetadata);

    void insertTopicBrokerInfo(TopicBrokerInfo topicBrokerInfo);

    void delete(long id);

    void update(TopicMetadata topicMetadata);

    void updateTopicBrokerInfo(@Param("topic") Topic topic,
                               @Param("brokerId") long brokerId);

    TopicMetadata getMetadata(@Param("topicId") long topicId);

    TopicMetadata getMetadataByTopic(@Param("clusterName") String clusterName,
                                     @Param("topic") String topic);

    /**
     * Refresh sync_time column to current timestamp.
     * @param brokerId Broker ID.
     * @param topicId Topic ID.
     */
    void refresh(@Param("brokerId") long brokerId,
                 @Param("topicId") long topicId);

    /**
     * List by project or statusIds.
     * @param projectId Project ID.
     * @return List of topics.
     */
    List<TopicMetadata> list(@Param("projectId") long projectId,
                             @Param("statusIds") int[] statusIds,
                             @Param("cluster") String cluster);

    List<Long> getProjects(long topicId, String topic);

    List<TopicAvailability> queryTopicsAvailability();

    List<DataCenter> queryAllowedDC(long topicId);

    boolean isDCAllowed(@Param("topicId") long topicId,
                        @Param("dcId")long dcId);

    void insertDCAllowed(@Param("topicId") long topicId,
                         @Param("dcId")long dcId,
                         @Param("status")Status status);

    List<TopicBrokerInfo> queryTopicBrokerInfo(@Param("topicId")long topicId,
                                               @Param("brokerId")long brokerId,
                                               @Param("dc") int dc);

    List<Long> queryAssociatedConsumerGroup(@Param("topicId") long topicId);

    void connectProject(@Param("topicId") long topicId, @Param("projectId") long projectId);

    void disconnectProject(@Param("topicId") long topicId, @Param("projectId") long projectId);

    /**
     * Method to test mybatis.
     *
     * @param id chair ID.
     * @return {@link Chair} instance.
     */
    Chair getChair(@Param("chairId") long id);
}
