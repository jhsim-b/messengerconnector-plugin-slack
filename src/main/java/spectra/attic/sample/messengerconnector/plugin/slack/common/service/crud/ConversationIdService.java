package spectra.attic.sample.messengerconnector.plugin.slack.common.service.crud;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class ConversationIdService {
    private static final Map<String, String> conversationIdsMap = new ConcurrentHashMap<>();

    public String find(String userId) {
        return conversationIdsMap.get(userId);
    }

    public String enrol(String userId) {
        if (!Optional.ofNullable(conversationIdsMap.get(userId)).isPresent()) {
            conversationIdsMap.put(userId, UUID.randomUUID().toString());
        }
        return conversationIdsMap.get(userId);
    }

    public void remove() {
        conversationIdsMap.clear();
    }
}
