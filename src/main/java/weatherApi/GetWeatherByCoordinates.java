package weatherApi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import java.util.Properties;

public class GetWeatherByCoordinates extends AbstractApiMethodV2 {
    public GetWeatherByCoordinates() {
        super(null, "apiWeather/getWeatherByCoordinates/rs.json", new Properties());
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }

}
