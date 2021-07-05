package org.azd.work;

import org.azd.enums.IterationsTimeFrame;
import org.azd.enums.RequestMethod;
import org.azd.exceptions.AzDException;
import org.azd.exceptions.DefaultParametersException;
import org.azd.helpers.JsonMapper;
import org.azd.interfaces.WorkDetails;
import org.azd.connection.Connection;
import org.azd.utils.ResourceId;
import org.azd.work.types.IterationWorkItems;
import org.azd.work.types.TeamSettingsIteration;
import org.azd.work.types.TeamSettingsIterations;

import java.util.HashMap;
import java.util.Map;

import static org.azd.helpers.URLHelper.encodeSpace;
import static org.azd.utils.Client.request;

/***
 * WORK class to manage work API
 */
public class WorkApi implements WorkDetails {
    /***
     * Connection object
     */
    private final Connection CONNECTION;
    private final JsonMapper MAPPER = new JsonMapper();
    private final String AREA = "work";


    /***
     * Instantiate the class with instance of AzDDefaultParameters
     * @param connection Connection object
     */
    public WorkApi(Connection connection) { this.CONNECTION = connection; }

    /***
     * Get a team's iterations
     * @param teamName Team ID or team name
     * @return {@link TeamSettingsIterations}
     * @throws DefaultParametersException user must instantiate AzDDefaultParameters before calling this method
     * @throws AzDException Exception handler
     */
    @Override
    public TeamSettingsIterations getTeamSettingsIterations(String teamName) throws DefaultParametersException, AzDException {
        String r = request(RequestMethod.GET, CONNECTION, ResourceId.WORK,
                (CONNECTION.getProject() + "/" + encodeSpace(teamName)),
                AREA,null , "teamsettings/iterations", WorkVersion.VERSION, null, null);

        return MAPPER.mapJsonResponse(r, TeamSettingsIterations.class);
    }

    /***
     * Get a team's iterations using timeframe filter
     * @param teamName Team ID or team name
     * @param timeFrame A filter for which iterations are returned based on relative time.
     * Only 'Current' is supported currently. {@link IterationsTimeFrame}
     * @return {@link TeamSettingsIterations}
     * @throws DefaultParametersException user must instantiate AzDDefaultParameters before calling this method
     * @throws AzDException Exception handler
     */
    @Override
    public TeamSettingsIterations getTeamSettingsIterations(String teamName, IterationsTimeFrame timeFrame) throws DefaultParametersException, AzDException {

        var q = new HashMap<String, Object>(){{
            put("$timeframe", timeFrame.toString().toLowerCase());
        }};

        String r = request(RequestMethod.GET, CONNECTION, ResourceId.WORK,
                (CONNECTION.getProject() + "/" + encodeSpace(teamName)),
                AREA,null , "teamsettings/iterations", WorkVersion.VERSION, q, null);

        return MAPPER.mapJsonResponse(r, TeamSettingsIterations.class);
    }

    /***
     * Get work items for iteration
     * @param teamName Team ID or team name
     * @param iterationId ID of the iteration
     * @return {@link TeamSettingsIterations}
     * @throws DefaultParametersException user must instantiate AzDDefaultParameters before calling this method
     * @throws AzDException Exception handler
     */
    @Override
    public IterationWorkItems getTeamIterationWorkItems(String teamName, String iterationId) throws DefaultParametersException, AzDException {
        String r = request(RequestMethod.GET, CONNECTION, ResourceId.WORK,
                (CONNECTION.getProject() + "/" + encodeSpace(teamName)),
                AREA + "/teamsettings/iterations",iterationId , "workitems", WorkVersion.VERSION, null, null);

        return MAPPER.mapJsonResponse(r, IterationWorkItems.class);
    }

    /***
     * Get team's iteration by iterationId
     * @param teamName ID of the iteration
     * @param iterationId Team ID or team name
     * @return {@link TeamSettingsIterations}
     * @throws DefaultParametersException user must instantiate AzDDefaultParameters before calling this method
     * @throws AzDException Exception handler
     */
    @Override
    public TeamSettingsIteration getTeamSettingsIteration(String teamName, String iterationId) throws DefaultParametersException, AzDException {
        String r = request(RequestMethod.GET, CONNECTION, ResourceId.WORK,
                (CONNECTION.getProject() + "/" + encodeSpace(teamName)),
                AREA + "/teamsettings/iterations",iterationId , null, WorkVersion.VERSION, null, null);

        return MAPPER.mapJsonResponse(r, TeamSettingsIteration.class);
    }

    /***
     * Delete a team's iteration by iterationId
     * @param teamName Team ID or team name
     * @param iterationId ID of the iteration
     * @throws DefaultParametersException user must instantiate AzDDefaultParameters before calling this method
     * @throws AzDException Exception handler
     */
    @Override
    public void deleteTeamSettingsIteration(String teamName, String iterationId) throws DefaultParametersException, AzDException {
        try {
            String r = request(RequestMethod.DELETE, CONNECTION, ResourceId.WORK,
                        (CONNECTION.getProject() + "/" + encodeSpace(teamName)),
                        AREA + "/teamsettings/iterations", iterationId, null, WorkVersion.VERSION, null, null);
            if (!r.isEmpty()) MAPPER.mapJsonResponse(r, Map.class);
        } catch (DefaultParametersException | AzDException e) {
            throw e;
        }
    }

}
