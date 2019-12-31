import org.apache.jena.query.*;
import utils.Query;

public class app
{
    public static void main(String[] args)
    {
        /*
         * Create query Object
         */
        Query query = new Query();

        /*
         * Execute query and display result
         */

        ResultSet resultSetMcfReligion = query.getQueryExecutionReligion().execSelect();
        System.out.println("Documents with subject religion :");
        ResultSetFormatter.out(System.out, resultSetMcfReligion, query.getJenaQueryReligion());

        ResultSet resultSetMcfAuto = query.getQueryExecutionAuto().execSelect();
        System.out.println("Documents with subject autos :");
        ResultSetFormatter.out(System.out, resultSetMcfAuto, query.getJenaQueryAuto());

        ResultSet resultSetMcfIt = query.getQueryExecutionIt().execSelect();
        System.out.println("Documents with subject it :");
        ResultSetFormatter.out(System.out, resultSetMcfIt, query.getJenaQueryIt());

        ResultSet resultSetMcfRulesReligion = query.getQueryExecutionReligionMcf().execSelect();
        System.out.println("Documents with subject religion after rules established :");
        ResultSetFormatter.out(System.out, resultSetMcfRulesReligion, query.getJenaQueryReligion());

        ResultSet resultSetMcfRulesAuto = query.getQueryExecutionAutoMcf().execSelect();
        System.out.println("Documents with subject autos after rules established :");
        ResultSetFormatter.out(System.out, resultSetMcfRulesAuto, query.getJenaQueryAuto());

        ResultSet resultSetMcfRulesIt = query.getQueryExecutionItMcf().execSelect();
        System.out.println("Documents with subject it after rules established :");
        ResultSetFormatter.out(System.out, resultSetMcfRulesIt, query.getJenaQueryIt());
    }
}