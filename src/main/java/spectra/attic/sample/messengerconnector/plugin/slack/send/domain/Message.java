package spectra.attic.sample.messengerconnector.plugin.slack.send.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.coreasset.share.util.JsonSerializable;
import spectra.attic.coreasset.share.util.JsonUtil;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements JsonSerializable {
    private String messageId;

    private String channelUrl;

    private MessageOption option;

    public static Message fromJson(String json) {
        return JsonUtil.fromJson(json, Message.class);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
