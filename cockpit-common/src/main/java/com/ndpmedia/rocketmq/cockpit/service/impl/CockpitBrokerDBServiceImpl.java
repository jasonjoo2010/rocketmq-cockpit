package com.ndpmedia.rocketmq.cockpit.service.impl;

import com.ndpmedia.rocketmq.cockpit.model.Broker;
import com.ndpmedia.rocketmq.cockpit.mybatis.mapper.BrokerMapper;
import com.ndpmedia.rocketmq.cockpit.service.CockpitBrokerDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cockpitBrokerDBService")
public class CockpitBrokerDBServiceImpl implements CockpitBrokerDBService {

    @Autowired
    private BrokerMapper brokerMapper;

    @Override
    public boolean hasConsumerGroup(long brokerId, long consumerGroupId) {
        return brokerMapper.hasConsumerGroup(brokerId, consumerGroupId);
    }

    @Override
    public boolean hasTopic(long brokerId, long topicId) {
        return brokerMapper.hasTopic(brokerId, topicId);
    }

    @Override
    public void createConsumerGroup(long brokerId, long consumerGroupId) {
        brokerMapper.createConsumerGroup(brokerId, consumerGroupId);
    }

    @Override
    public Broker get(long brokerId, String brokerAddress) {

        if (brokerId > 0) {
            return brokerMapper.get(brokerId);
        } else if (null != brokerAddress) {
            return brokerMapper.getBrokerByAddress(brokerAddress);
        }

        return null;
    }

    @Override
    public List<Broker> list(String clusterName, String brokerName, int brokerId, int dc) {
        return brokerMapper.list(clusterName, brokerName, brokerId, dc, null);
    }
}
