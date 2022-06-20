package com.qaprosoft.carina.demo.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class PostExistUserMethod extends AbstractApiMethodV2 {
    public PostExistUserMethod() {
        super("api/users/_postExist/rq.json", "api/users/_postExist/rs.json", "api/users/user.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}