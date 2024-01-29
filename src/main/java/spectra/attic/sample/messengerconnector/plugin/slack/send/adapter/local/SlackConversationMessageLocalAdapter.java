package spectra.attic.sample.messengerconnector.plugin.slack.send.adapter.local;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spectra.attic.sample.messengerconnector.plugin.slack.send.service.flow.SlackSendMessageFlowService;
import spectra.attic.talk.messengerconnector.conversation.conversation.domain.enums.ThirdPartyChannelType;
import spectra.attic.talk.messengerconnector.conversation.message.domain.ConversationBusinessClosedMessage;
import spectra.attic.talk.messengerconnector.conversation.message.domain.ConversationBusinessDeletedMessage;
import spectra.attic.talk.messengerconnector.conversation.message.domain.ConversationBusinessMessage;
import spectra.attic.talk.messengerconnector.conversation.message.domain.ConversationBusinessWarningMessage;
import spectra.attic.talk.messengerconnector.thirdparty.message.send.adapter.ThirdPartyConversationMessageHandlerAdapter;
import spectra.attic.talk.messengerconnector.thirdparty.message.send.validation.annotation.ThirdPartyChannelTypeFilter;

@Slf4j
@Component
@RequiredArgsConstructor
@ThirdPartyChannelTypeFilter(ThirdPartyChannelType.NAVER)
public class SlackConversationMessageLocalAdapter implements ThirdPartyConversationMessageHandlerAdapter {
    private final SlackSendMessageFlowService slackSendMessageFlowService;

    @Override
    public void message(ConversationBusinessMessage conversationMessage) {
        log.debug("conversation message : {}", conversationMessage);
        slackSendMessageFlowService.send(conversationMessage.getMessage());
    }

    @Override
    public void closed(ConversationBusinessClosedMessage closedConversationMessage) {
        log.debug("conversation close : {}", closedConversationMessage);
        slackSendMessageFlowService.close();
    }

    @Override
    public void warning(ConversationBusinessWarningMessage warningConversationMessage) {

    }

    @Override
    public void delete(ConversationBusinessDeletedMessage conversationMessageKey) {

    }
}
