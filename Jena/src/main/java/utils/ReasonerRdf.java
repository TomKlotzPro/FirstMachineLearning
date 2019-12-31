package utils;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.util.PrintUtil;

public class ReasonerRdf {

    private GenericRuleReasoner reasonerRdf = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance().create(null);

    private Model model;

    private Rules rule  = new Rules();

    /*
     * This constructor register prefix and set our rules.
     */
    public ReasonerRdf(Model model){

        String exURI = "http://purl.org/dc/elements/1.1/";
        PrintUtil.registerPrefix("ex", exURI);
        String rdfURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
        PrintUtil.registerPrefix("rdf", rdfURI);

        reasonerRdf.setRules(org.apache.jena.reasoner.rulesys.Rule.parseRules(rule.getRules().toString()));
        this.model = model;
    }

    /*
     * getter for our Inference Model
     * @return QueryExecution
     */
    public InfModel getInfModel() {
        return  ModelFactory.createInfModel(reasonerRdf, model.getModel());
    }
}
