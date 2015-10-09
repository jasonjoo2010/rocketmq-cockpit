package com.ndpmedia.rocketmq.cockpit.service.impl;

import com.ndpmedia.rocketmq.cockpit.model.ConsumerGroup;
import com.ndpmedia.rocketmq.cockpit.model.Status;
import com.ndpmedia.rocketmq.cockpit.mybatis.mapper.ConsumerGroupMapper;
import com.ndpmedia.rocketmq.cockpit.service.CockpitConsumerGroupDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("cockpitConsumerGroupDBService")
public class CockpitConsumerGroupDBServiceImpl implements CockpitConsumerGroupDBService {
    @Autowired
    private ConsumerGroupMapper consumerGroupMapper;

    @Override
    public ConsumerGroup get(long consumerGroupId, String consumerGroupName) {
        if (consumerGroupId <=0 && null == consumerGroupName) {
            throw new IllegalArgumentException("Either consumer group ID or Name should be present");
        }

        return consumerGroupMapper.get(consumerGroupId, consumerGroupName);
    }

    @Override
    public void activate(long consumerGroupId) {
        ConsumerGroup consumerGroup = consumerGroupMapper.get(consumerGroupId, null);
        if (null != consumerGroup && consumerGroup.getStatus() != Status.ACTIVE) {
            consumerGroup.setStatus(Status.ACTIVE);
            consumerGroupMapper.update(consumerGroup);
        }
    }


    @Override
    @Transactional
    public void insert(ConsumerGroup consumerGroup, long projectId) {
        consumerGroupMapper.insert(consumerGroup);
        consumerGroupMapper.connectProject(consumerGroup.getId(), projectId);
    }

    @Transactional
    @Override
    public void delete(long consumerGroupId) {
        consumerGroupMapper.disconnectProject(consumerGroupId, 0);
        consumerGroupMapper.delete(consumerGroupId);
    }

    @Override
    public void refresh(long brokerId, long consumerGroupId) {
        consumerGroupMapper.refresh(brokerId, consumerGroupId);
    }
}
