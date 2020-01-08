package edu.uci.ics.luisae.service.movies.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uci.ics.luisae.service.movies.Base.Result;
import edu.uci.ics.luisae.service.movies.MoviesService;
import edu.uci.ics.luisae.service.movies.logger.ServiceLogger;
import edu.uci.ics.luisae.service.movies.models.IdmModels.PrivilegeRequest;
import edu.uci.ics.luisae.service.movies.models.IdmModels.RegisterAndPrivilegeResponse;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class Intercommunication {
    public static boolean hasPrivilege(String email, int plevel){
        ServiceLogger.LOGGER.info(email);
        PrivilegeRequest request = new PrivilegeRequest(email,plevel);
        RegisterAndPrivilegeResponse privilegeResponse;

        String servicePath = getIdmPath();
        String privilegePath =  MoviesService.getIdmConfigs().getPrivilegePath();
        ServiceLogger.LOGGER.info("url: " + servicePath);
        Client client = ClientBuilder.newClient();
        client.register(JacksonFeature.class);

        WebTarget webTarget = client.target(servicePath).path(privilegePath);

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);

        Response response = invocationBuilder.post(Entity.entity(request, MediaType.APPLICATION_JSON));

        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonText = response.readEntity(String.class);
            ServiceLogger.LOGGER.info(jsonText);
            privilegeResponse = mapper.readValue(jsonText, RegisterAndPrivilegeResponse.class);
            if(jsonText.endsWith("140}"))
                privilegeResponse.setResult(Result.SUFFICIENT_PLEVEL);
            else
                privilegeResponse.setResult(Result.INSUFFICIENT_PLEVEL);
            ServiceLogger.LOGGER.info("Successfully mapped to POJO: INTERCOMMUNICATION PRIVILEGE");
        }catch(IOException e){
            ServiceLogger.LOGGER.warning("Unable to map response to POJO: INTERCOMMUNICATION PRIVILEGE");
            return false;
        }
        //do stuff with data from response
        return (privilegeResponse.getResultCode() == Result.SUFFICIENT_PLEVEL.getResultCode());

    }

    private static String getIdmPath(){
        return MoviesService.getIdmConfigs().getScheme() + MoviesService.getIdmConfigs().getHostName() + ":"
                + MoviesService.getIdmConfigs().getPort() + MoviesService.getIdmConfigs().getPath();
    }

}
