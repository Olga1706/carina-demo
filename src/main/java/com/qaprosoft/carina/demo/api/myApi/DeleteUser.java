package com.qaprosoft.carina.demo.api.myApi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

public class DeleteUser extends AbstractApiMethodV2 {
    public DeleteUser() {
        super("api/users/_deleteUser/rq.json", "api/users/_deleteUser/rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
