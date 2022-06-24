package com.qaprosoft.carina.demo.api.myApi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PutUser extends AbstractApiMethodV2 {
    public PutUser() {
        super("api/users/_putUser/rq.json", "api/users/_putUser/rs.json", "api/users/user.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}

