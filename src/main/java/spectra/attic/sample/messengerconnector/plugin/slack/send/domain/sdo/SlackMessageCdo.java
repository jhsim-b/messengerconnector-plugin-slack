package spectra.attic.sample.messengerconnector.plugin.slack.send.domain.sdo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SlackMessageCdo implements JsonSerializable {
    private String text;

    @Override
    public String toString() {
        return toJson();
    }
}
