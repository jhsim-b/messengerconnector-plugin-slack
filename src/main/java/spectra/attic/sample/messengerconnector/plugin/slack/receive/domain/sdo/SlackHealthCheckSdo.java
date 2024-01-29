package spectra.attic.sample.messengerconnector.plugin.slack.receive.domain.sdo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.coreasset.share.util.JsonSerializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SlackHealthCheckSdo implements JsonSerializable {
    private String token;

    private String challenge;

    private String type;

    @Override
    public String toString() {
        return toJson();
    }
}
