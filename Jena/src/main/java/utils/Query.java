package utils;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;

public class Query {

    private  String queryReligion = "PREFIX ex:<http://purl.org/dc/elements/1.1/>\n" +
            "SELECT ?religion \n" +
            "WHERE { ?religion ex:is_about \"religion\"}";

    private String queryAuto = "PREFIX ex:<http://purl.org/dc/elements/1.1/>\n" +
            "SELECT ?autos \n" +
            "WHERE { ?autos ex:is_about \"autos\"}";

    private String queryIt = "PREFIX ex:<http://purl.org/dc/elements/1.1/>\n" +
            "SELECT ?it \n" +
            "WHERE { ?it ex:is_about \"it\"}";

    private Model model = new Model();

    private ReasonerRdf reasonerRdf = new ReasonerRdf(model);

    private org.apache.jena.query.Query jenaQueryReligion = QueryFactory.create(queryReligion) ;

    private org.apache.jena.query.Query jenaQueryAuto =  QueryFactory.create(queryAuto);

    private org.apache.jena.query.Query jenaQueryIt =  QueryFactory.create(queryIt);


    /*
     * getter for our Religion query
     * @return org.apache.jena.query.Query
     */
    public org.apache.jena.query.Query getJenaQueryReligion() {
        return jenaQueryReligion;
    }

    /*
     * getter for our Auto query
     * @return org.apache.jena.query.Query
     */
    public org.apache.jena.query.Query getJenaQueryAuto() {
        return jenaQueryAuto;
    }

    /*
     * getter for our It query
     * @return org.apache.jena.query.Query
     */
    public org.apache.jena.query.Query getJenaQueryIt() {
        return jenaQueryIt;
    }

    /*
     * getter for query execution on Auto
     * @return QueryExecution
     */
    public QueryExecution getQueryExecutionAuto(){
        return QueryExecutionFactory.create(jenaQueryAuto, model.getModel());
    }

    /*
     * getter for query execution on Religion
     * @return QueryExecution
     */
    public QueryExecution getQueryExecutionReligion(){
        return QueryExecutionFactory.create(jenaQueryReligion, model.getModel());
    }

    /*
     * getter for query execution on It
     * @return QueryExecution
     */
    public QueryExecution getQueryExecutionIt(){
        return QueryExecutionFactory.create(jenaQueryIt, model.getModel());
    }

    /*
     * getter for query execution on Auto after rules
     * @return QueryExecution
     */
    public QueryExecution getQueryExecutionAutoMcf(){
        return QueryExecutionFactory.create(jenaQueryAuto, reasonerRdf.getInfModel());
    }

    /*
     * getter for query execution on religion after rules
     * @return QueryExecution
     */
    public QueryExecution getQueryExecutionReligionMcf(){
        return QueryExecutionFactory.create(jenaQueryReligion, reasonerRdf.getInfModel());
    }

    /*
     * getter for query execution on It after rules
     * @return QueryExecution
     */
    public QueryExecution getQueryExecutionItMcf(){
        return QueryExecutionFactory.create(jenaQueryIt, reasonerRdf.getInfModel());
    }
}
