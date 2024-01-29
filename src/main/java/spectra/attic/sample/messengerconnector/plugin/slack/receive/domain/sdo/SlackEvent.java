package spectra.attic.sample.messengerconnector.plugin.slack.receive.domain.sdo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SlackEvent implements JsonSerializable {
    private String type;

    private String text;

    private String user;

    private String channel;

    private String ts;

    @Override
    public String toString() {
        return toJson();
    }
}
