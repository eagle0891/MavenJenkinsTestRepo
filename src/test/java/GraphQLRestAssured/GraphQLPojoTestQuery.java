package GraphQLRestAssured;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GraphQLPojoTestQuery {

    private String query;
    private Object variables;

    public GraphQLPojoTestQuery() {

    }

//    public String getQuery() {
//        return query;
//    }
//
//    public void setQuery(String query) {
//        this.query = query;
//    }
//
//    public Object getVariables() {
//        return variables;
//    }
//
//    public void setVariables(Object variables) {
//        this.variables = variables;
//    }
}
