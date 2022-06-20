package weatherApi;
import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

import java.util.Properties;

public class GetWeatherByFiveDays extends AbstractApiMethodV2 {
    public GetWeatherByFiveDays() {
        super(null, "apiWeather/getWeatherByFiveDays/rs.json", new Properties());
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}