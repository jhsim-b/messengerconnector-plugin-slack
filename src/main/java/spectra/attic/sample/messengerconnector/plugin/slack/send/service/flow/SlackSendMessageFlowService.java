package spectra.attic.sample.messengerconnector.plugin.slack.send.service.flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spectra.attic.sample.messengerconnector.plugin.slack.common.service.crud.ConversationIdService;
import spectra.attic.sample.messengerconnector.plugin.slack.send.client.SlackWebhookClient;
import spectra.attic.sample.messengerconnector.plugin.slack.send.domain.Message;
import spectra.attic.sample.messengerconnector.plugin.slack.send.domain.sdo.SlackMessageCdo;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackSendMessageFlowService {
    private final SlackWebhookClient slackWebhookClient;

    private final ConversationIdService conversationIdService;

    public void send(String message) {
        try {
            String textMessage = Message.fromJson(message).getOption().getMessage();
            slackWebhookClient.send(new SlackMessageCdo(textMessage));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void close() {
        conversationIdService.remove();
    }
}
