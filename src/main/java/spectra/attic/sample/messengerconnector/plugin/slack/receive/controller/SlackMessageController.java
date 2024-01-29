package spectra.attic.sample.messengerconnector.plugin.slack.receive.controller;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spectra.attic.sample.messengerconnector.plugin.slack.receive.domain.sdo.SlackHealthCheckSdo;
import spectra.attic.sample.messengerconnector.plugin.slack.receive.domain.sdo.SlackMessageSdo;
import spectra.attic.sample.messengerconnector.plugin.slack.receive.service.flow.SlackSourceMessageFlowService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("slack")
public class SlackMessageController {
    private final SlackSourceMessageFlowService slackSourceMessageFlowService;

    @PostMapping("ping")
    public String ping(@Valid @RequestBody SlackHealthCheckSdo slackHealthCheckSdo) {
        log.debug("slack healthcheck : {} ", slackHealthCheckSdo);
        return slackHealthCheckSdo.getChallenge();
    }

    @PostMapping
    public void messages(@Valid @RequestBody SlackMessageSdo slackMessageSdo) {
        log.debug("messages : {} ", slackMessageSdo);
        slackSourceMessageFlowService.message(slackMessageSdo);
    }
}
