//package com.sparql.demoblazegraph.service.impl;
//
//import com.bigdata.journal.Options;
//import com.bigdata.rdf.sail.BigdataSail;
//import com.bigdata.rdf.sail.BigdataSailRepository;
//import com.sparql.demoblazegraph.service.LandingService;
//import org.openrdf.query.*;
//import org.openrdf.repository.Repository;
//import org.openrdf.repository.RepositoryConnection;
//import org.openrdf.repository.RepositoryException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//
//@Service
//public class LandingServiceImpl implements LandingService {
//    @Override
//    public ResponseEntity<?> getResult() throws RepositoryException, MalformedQueryException {
//        final Properties props = new Properties();
//        props.put(Options.FILE, "/rows.rdf");
//
//        final BigdataSail sail = new BigdataSail(props);
//        final Repository repo = new BigdataSailRepository(sail);
//        repo.initialize();
//        try {
//            RepositoryConnection cxn = repo.getConnection();
//
//            // open connection
//            if (repo instanceof BigdataSailRepository) {
//                cxn = ((BigdataSailRepository) repo).getReadOnlyConnection();
//            } else {
//                cxn = repo.getConnection();
//            }
//
//            // evaluate sparql query
//            try {
//
//                final TupleQuery tupleQuery = cxn
//                        .prepareTupleQuery(QueryLanguage.SPARQL,
//                                "SELECT * {?s ?p ?o}");
//                final TupleQueryResult result = tupleQuery.evaluate();
//                try {
//                    while (result.hasNext()) {
//                        final BindingSet bindingSet = result.next();
//                        System.out.println(bindingSet);
//                    }
//                } finally {
//                    result.close();
//                }
//
//            } catch (QueryEvaluationException e) {
//                e.printStackTrace();
//            } finally {
//                // close the repository connection
//                cxn.close();
//            }
//
//        } finally {
//            repo.shutDown();
//        }
//        return null;
//    }
//}
