package utils;

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class Model {

    private org.apache.jena.rdf.model.Model model = ModelFactory.createDefaultModel();

    private String file = "data/rdf_generated.xml";

    private InputStream inputModel = FileManager.get().open(file);

    /*
     * This constructor read our predefined file
     */
    public Model(){
        InputStream inputModel = FileManager.get().open(file);
        model.read(inputModel, null);
    }
    /*
     * getter for our model
     * @return org.apache.jena.rdf.model.Model
     */
    public org.apache.jena.rdf.model.Model getModel() {
        return model;
    }
}
