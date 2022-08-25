package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ActiveMQConfig;
import model.ActiveMQMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ActiveMQProducer {

//    private static final Logger log = LoggerFactory.getLogger(ActiveMQProducer.class);

    private List<JmsTemplate> jmsTemplates;

    private AtomicInteger current = new AtomicInteger(0);

    public ActiveMQProducer() {
        this.jmsTemplates = ActiveMQConfig.getJmsQueueTemplates();
    }

    private JmsTemplate findJmsTemplate_LoadBalanced() {
        int cur = current.getAndIncrement();
        int index = cur % this.jmsTemplates.size();

        System.out.println("==** All JmsTemplates : " + this.jmsTemplates);
        System.out.println("\tFind Load balanced JmsTemplate[ " + index + " ]");

        return this.jmsTemplates.get(index);
    }

    public void sendMessage(ActiveMQMessage activeMQMessage) throws JsonProcessingException {
//        log.info("==** ActiveMQ Message: " + activeMQMessage);
        System.out.println("==** ActiveMQ Message: " + activeMQMessage);

        try {
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(activeMQMessage);

            JmsTemplate jmsTemplate = findJmsTemplate_LoadBalanced();

            jmsTemplate.send(ActiveMQConfig.EMAIL_PROCESSING_QUEUE, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
        } catch (Exception ex) {
            System.out.println("ERROR in sending message to queue");
        }

        System.out.println("Message has been sent !!!");
    }
}