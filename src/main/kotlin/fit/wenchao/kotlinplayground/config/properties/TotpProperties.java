package fit.wenchao.kotlinplayground.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "totp")
public class TotpProperties {
    private String tokenParams = "token_raw_key=key:token_aad=aad";
}
