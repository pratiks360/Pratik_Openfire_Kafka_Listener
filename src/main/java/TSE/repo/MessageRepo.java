package TSE.repo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class MessageRepo {
    public void insertChatRecords(String from, String to, String message, String timestamp, String deviceID) {
        System.out.println("INTO MONGO");
        String conn_String = System.getenv("MONGO_URI");
        MongoClientURI uri = new MongoClientURI(conn_String);
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("chat_retension");
        MongoCollection<Document> table = db.getCollection("chat_logs");
        Document doc = new Document("from", from);
        doc.append("to", to);
        doc.append("message", message);
        doc.append("timestamp", timestamp);
        doc.append("deviceID", deviceID);
        table.insertOne(doc);
    }


}
