package com.sparql.demoblazegraph.config;

import net.minidev.json.JSONObject;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.shared.JenaException;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class JenaConfig {

    private static QueryExecution qe;
    private static String rdfFile = "rows.rdf";

    public static ResultSet execQuery(String queryString){

        OntModel ontoModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        try {
            InputStream in = FileManager.get().open(rdfFile);
            try {
                ontoModel.read(in, null);

                Query query = QueryFactory.create(queryString);

                //Execute the query and obtain results
                qe = QueryExecutionFactory.create(query, ontoModel);
                ResultSet results = qe.execSelect();

                // Output query results
                //ResultSetFormatter.out(System.out, results, query);

                return results;

            } catch (Exception e) {
                e.printStackTrace();
            }
        //    LOGGER.info("RDF " + rdfFile + " loaded.");
        } catch (JenaException je) {
            System.err.println("ERROR" + je.getMessage());
            je.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static List<JSONObject> getItems(String queryString) {
        ResultSet resultSet = execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            System.out.println(solution);
            obj.put("id",x);
            //obj.put("item",solution.get("s").toString());
            obj.put("name",solution.get("name").toString());
            list.add(obj);
           // System.out.println(solution);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }

    public static List<JSONObject> findPlaces(String queryString) {
        ResultSet resultSet = execQuery(queryString);
        List<JSONObject> list = new ArrayList<>();
        int x=0;
        while (resultSet.hasNext()) {
            x++;
            JSONObject obj = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            System.out.println(solution);
            obj.put("id",x);
            obj.put("name",solution.get("name").toString());
            obj.put("zipcode",solution.get("zipcode").toString());
            obj.put("neighborhood",solution.get("neighborhood").toString());
            obj.put("policedistrict",solution.get("policedistrict").toString());
            list.add(obj);
        }

        // Important ‑ free up resources used running the query
        //qe.close();
        return list;
    }
}
