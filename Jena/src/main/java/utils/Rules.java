package utils;

public class Rules {

    private  StringBuilder rules = new StringBuilder();

    /*
     * This constructor defines all rules for our model
     */
    public Rules(){
        rules.append("[rule1: (?x ex:is_about \"atheism\") -> (?x ex:is_about \"religion\")]");
        rules.append("[rule2: (?x ex:is_about \"christian\") -> (?x ex:is_about \"religion\")]");
        rules.append("[rule3: (?x ex:is_about \"motorcycles\") -> (?x ex:is_about \"autos\")]");
        rules.append("[rule4: (?x ex:is_about \"misc\") -> (?x ex:is_about \"it\")]");
        rules.append("[rule5: (?x ex:is_about \"graphics\") -> (?x ex:is_about \"it\")]");
        rules.append("[rule6: (?x ex:is_about \"hardware\") -> (?x ex:is_about \"it\")]");
        rules.append("[rule7: (?x ex:is_about \"x\") -> (?x ex:is_about \"it\")]");
    }
    /*
     * getter for our rules
     * @return StringBuilder
     */
    public StringBuilder getRules() {
        return rules;
    }
}
