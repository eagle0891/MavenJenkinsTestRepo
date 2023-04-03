package GraphQLRestAssured.Modules;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphQLDataFields {
    private static final ArrayList<String> ModuleConfigAllStarWarsFilms_dataFields = new ArrayList<>(Arrays.asList("allFilms", "films", "title"));
    private static final ArrayList<String> ModuleConfigAlbumsListQuery_dataFields = new ArrayList<>(Arrays.asList("Album", "AlbumId", "Title"));
    private static final ArrayList<String> ModuleConfigEmployeeListQuery_dataFields = new ArrayList<>(Arrays.asList("Employee", "Address", "BirthDate", "City", "Country", "Email"));

    public static ArrayList<String> returnModuleArray(String moduleType){
        switch(moduleType){
            case "allStarWarsFilmsQuery":
                return ModuleConfigAllStarWarsFilms_dataFields;
            case "albumsListQuery":
                return ModuleConfigAlbumsListQuery_dataFields;
            case "employeeListQuery":
                return ModuleConfigEmployeeListQuery_dataFields;
            default:
                System.out.println("ERROR :: DATAFIELD ATTRIBUTES MISSING OR INVALID :: please check and try again");
                return null;
        }
    }
}
