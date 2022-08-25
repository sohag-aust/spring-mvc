package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableJms
public class ActiveMQConfig {
    public static final String EMAIL_PROCESSING_QUEUE = "EMAIL_PROCESSING_QUEUE";

    public static List<JmsTemplate> jmsQueueTemplates = new ArrayList<>();

    private String brokerUrl1 = "tcp://localhost:61616";
    private String brokerUrl2 = "tcp://localhost:61716";

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(EMAIL_PROCESSING_QUEUE);
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory1() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl1);
        return activeMQConnectionFactory;
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory2() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl2);
        return activeMQConnectionFactory;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public JmsTemplate jmsTemplate1() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(activeMQConnectionFactory1());
        template.setMessageConverter(messageConverter());
        jmsQueueTemplates.add(template);
        return template;
    }

    @Bean
    public JmsTemplate jmsTemplate2() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(activeMQConnectionFactory2());
        template.setMessageConverter(messageConverter());
        jmsQueueTemplates.add(template);
        return template;
    }

    public static List<JmsTemplate> getJmsQueueTemplates() {
        return jmsQueueTemplates;
    }
}
