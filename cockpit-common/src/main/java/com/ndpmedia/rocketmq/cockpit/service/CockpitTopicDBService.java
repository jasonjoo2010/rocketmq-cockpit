package com.ndpmedia.rocketmq.cockpit.service;

import com.ndpmedia.rocketmq.cockpit.model.*;

import java.util.List;

public interface CockpitTopicDBService extends CockpitTopicBaseService {

    /**
     * get topic list by different status
     * @param statuses topic status
     * @return topic list
     */
    List<TopicMetadata> getTopics(Status... statuses);

    /**
     * get topic list by topic name from database
     * @param topic topic name
     * @param clusterName Cluster Name.
     * @return topic list
     */
    TopicMetadata getTopic(String clusterName, String topic);

    TopicMetadata getTopic(long topicId);

    List<TopicAvailability> queryTopicsAvailability(Status... statuses);

    /**
     * update topic status to active on database
     * @param id the topic id on database
     * @return result
     */
    boolean activate(long id);

    boolean activate(long topicId, long brokerId);

    /**
     * update topic status to del on database
     * @param id the topic id on database
     * @return result
     */
    boolean deactivate(long id);

    boolean deactivate(long topicId, long brokerId);


    /**
     * add topic to database by project
     * @param topicBrokerInfo topicBrokerInfo
     * @param projectId ID of the project this topic will be added to.
     */
    void insert(long projectId, TopicBrokerInfo topicBrokerInfo);

    void insert(TopicMetadata topicMetadata);

    void update(TopicMetadata topicMetadata);

    void update(TopicBrokerInfo topicBrokerInfo);

    void insert(TopicMetadata topicMetadata, long projectId);

    void insertTopicBrokerInfo(TopicBrokerInfo topicBrokerInfo);

    void refreshTopicBrokerInfo(long topicId, long brokerId);

    void insertTopicProjectInfo(long topicId, long projectId);

    /**
     * delete topic and remove topic-project relationship from database
     * @param topicId Topic id
     * @param projectId Project id
     */
    void remove(long topicId, long projectId);

    boolean exists(String clusterName, String topic);

    List<Long> getProjectIDs(long topicId, String topic);

    boolean isDCAllowed(long topicId, long dcId);

    void addDCAllowed(long topicId, long dcId, Status status);

    List<DataCenter> queryAllowedDC(long topicId);

    List<TopicBrokerInfo> queryEndangeredTopicBrokerInfoList(long brokerId);

    List<TopicBrokerInfo> queryApprovedTopicsByBroker(long brokerId);

    List<TopicBrokerInfo> queryTopicBrokerInfoByTopic(long topicId, long brokerId, int dc);

    List<TopicBrokerInfo> queryTopicBrokerInfo(long topicId, long brokerId, int dc);

    List<Long> queryAssociatedConsumerGroup(long topicId);
}
