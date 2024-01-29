package spectra.attic.sample.messengerconnector.plugin.slack.receive.domain.sdo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SlackMessageSdo implements JsonSerializable {
    private String token;

    private SlackEvent event;

    private String event_ts;

    @Override
    public String toString() {
        return toJson();
    }

    public boolean hasUserMessage() {
        return StringUtils.isNotEmpty(this.event.getUser());
    }
}
