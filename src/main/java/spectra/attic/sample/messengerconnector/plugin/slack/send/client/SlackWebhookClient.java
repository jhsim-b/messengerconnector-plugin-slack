package spectra.attic.sample.messengerconnector.plugin.slack.send.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spectra.attic.coreasset.share.adapter.config.FeignConfiguration;
import spectra.attic.sample.messengerconnector.plugin.slack.send.domain.sdo.SlackMessageCdo;
import spectra.attic.talk.messengerconnector.share.config.FeignRetryConfiguration;

@FeignClient(
    contextId = "spectra.attic.sample.messengerconnector.plugin.slack.send.client.SlackWebhookClient",
    url = "https://hooks.slack.com/services/",
    name = "slackWebhookClient",
    configuration = {FeignConfiguration.class, FeignRetryConfiguration.class},
    primary = false
)
public interface SlackWebhookClient {
    @PostMapping
    void send(@RequestBody SlackMessageCdo slackMessageCdo);
}

