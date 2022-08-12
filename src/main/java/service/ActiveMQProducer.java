package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ActiveMQConfig;
import model.ActiveMQMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class ActiveMQProducer {

//    private static final Logger log = LoggerFactory.getLogger(ActiveMQProducer.class);

    private final JmsTemplate jmsTemplate;

    public ActiveMQProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(ActiveMQMessage activeMQMessage) throws JsonProcessingException {
//        log.info("==** ActiveMQ Message: " + activeMQMessage);
        System.out.println("==** ActiveMQ Message: " + activeMQMessage);

        try {
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(activeMQMessage);

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