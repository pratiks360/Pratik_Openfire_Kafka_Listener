package TSE.utils;

import TSE.model.PacketMessage;
import TSE.repo.MessageRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listner {

    @Autowired
    MessageRepo msg;

    @KafkaListener(topics = "chat_logs", groupId = "groupId")
    public void listenGroupFoo(String message) throws JsonProcessingException {
        System.out.println(message);
        ObjectMapper mapper = new ObjectMapper();
        PacketMessage data = mapper.readValue(message, PacketMessage.class);

        msg.insertChatRecords(data.getFrom(), data.getTo(), data.getMessage(), data.getTimestamp(), data.getDeviceID());
    }
}
