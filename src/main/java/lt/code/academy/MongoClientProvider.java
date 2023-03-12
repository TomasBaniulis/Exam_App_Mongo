package lt.code.academy;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoClientProvider {

    private static MongoClient client;

    public MongoClientProvider() {}

    CodecRegistry registry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
    CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), registry);
    MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(codecRegistry).build();

    public static MongoClient getClient (){
        if (client == null) {
            new MongoClientProvider();
        }
        return client;
    }
}

