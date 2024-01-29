package spectra.attic.sample.messengerconnector.plugin.slack.receive.service.flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import spectra.attic.coreasset.share.domain.Metadata;
import spectra.attic.sample.messengerconnector.plugin.slack.common.service.crud.ConversationIdService;
import spectra.attic.sample.messengerconnector.plugin.slack.receive.domain.sdo.SlackMessageSdo;
import spectra.attic.talk.messengerconnector.conversation.conversation.adapter.ConversationAdapter;
import spectra.attic.talk.messengerconnector.conversation.conversation.domain.enums.BusinessChannelType;
import spectra.attic.talk.messengerconnector.conversation.conversation.domain.enums.ThirdPartyChannelType;
import spectra.attic.talk.messengerconnector.conversation.conversation.sdo.ConversationCdo;
import spectra.attic.talk.messengerconnector.conversation.conversation.sdo.ConversationChannelCdo;
import spectra.attic.talk.messengerconnector.thirdparty.message.receive.adapter.ThirdPartyMessageAdapter;
import spectra.attic.talk.messengerconnector.thirdparty.message.receive.sdo.ThirdPartyMessage;
import spectra.attic.talk.messengerconnector.thirdparty.message.receive.sdo.enums.ThirdPartyMessageType;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackSourceMessageFlowService {
    private final ConversationAdapter conversationAdapter;

    private final ThirdPartyMessageAdapter thirdPartyMessageAdapter;

    private final ConversationIdService conversationIdGenerator;

    public void message(SlackMessageSdo slackMessageSdo) {
        if (slackMessageSdo.hasUserMessage()) {
            registerChannel(slackMessageSdo.getEvent().getUser(), slackMessageSdo.getEvent().getChannel());
            registerMessage(slackMessageSdo);
        }
    }

    private void registerChannel(String userId, String channel) {
        String conversationId = conversationIdGenerator.enrol(userId);
        log.debug("conversationId : {}", conversationId);
        if (BooleanUtils.isFalse(conversationAdapter.exists(conversationId))) {
            conversationAdapter.register(new ConversationCdo(conversationId, new ConversationChannelCdo(channel, ThirdPartyChannelType.NAVER, BusinessChannelType.GROUP, false), Metadata.empty()));
        }
    }


    private void registerMessage(SlackMessageSdo slackMessageSdo) {
        thirdPartyMessageAdapter.message(
            conversationIdGenerator.find(slackMessageSdo.getEvent().getUser()),
            ThirdPartyMessage.create(
                slackMessageSdo.getEvent().getUser(),
                ThirdPartyMessageType.TEXT,
                slackMessageSdo.getEvent().getText(),
                Metadata.empty()
            ));
    }
}
