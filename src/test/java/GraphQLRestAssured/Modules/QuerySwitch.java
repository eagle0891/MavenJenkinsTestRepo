package GraphQLRestAssured.Modules;

import GraphQLRestAssured.POJO.ModuleConfigQueries;

public class QuerySwitch extends ModuleConfigQueries {

    static String getRequestModuleQuery(String requestModuleToTest, String... injectorValues) {
        switch (requestModuleToTest) {
            case "allStarWarsFilmsQuery":
                return ModuleConfigQueries.returnModuleConfigAllStarWarsFilmsQuery();
            case "albumsListQuery":
                return ModuleConfigQueries.returnModuleConfigAlbumsListQuery();
            case "employeeListQuery":
                return ModuleConfigQueries.returnModuleConfigEmployeeListQuery();
            default:
                System.out.println("***** INCORRECT MODULE QUERY PASSED FROM SCENARIO *****: Request query passed from scenario = \"" + requestModuleToTest + "\" - ensure expected and valid request query is injected");
                return null;
        }
    }
}
