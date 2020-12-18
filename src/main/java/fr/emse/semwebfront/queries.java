/**
 *
 *                      UJM * EMSE
 *
 *            Hamed RAHIMI * Aleksei PASHININ
 *
 *                 Semantic Web Project
 *
 */

package fr.emse.semwebfront;

public interface queries {

    public static final String GET_SNCF_DATA =  "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                "SELECT ?name ?lat ?long WHERE {" +
                                                        "?all  a geo:SpatialThing;" +
                                                        "rdfs:SubClassOf wd:Q13646;" +
                                                        "rdfs:label ?name;" +
                                                        "geo:long ?lat;" +
                                                        "geo:lat ?long ." +
                                                "}";

    public static final String GET_TGV_DATA =   "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                "SELECT ?name ?lat ?long WHERE {" +
                                                        "?all  a geo:SpatialThing;" +
                                                        "rdfs:SubClassOf wd:Q129337;" +
                                                        "rdfs:label ?name;" +
                                                        "geo:long ?lat;" +
                                                        "geo:lat ?long ." +
                                                "}";

    public static final String GET_POST_OFFICE_DATA =   "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                        "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                        "SELECT ?name ?lat ?long WHERE {" +
                                                                "?all  a geo:SpatialThing;" +
                                                                "rdfs:SubClassOf wd:Q35054;" +
                                                                "rdfs:label ?name;" +
                                                                "geo:long ?lat;" +
                                                                "geo:lat ?long ." +
                                                        "}";

    public static final String GET_MUSEUM_DATA =    "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                    "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                    "SELECT ?name ?lat ?long WHERE {" +
                                                            "?all  a geo:SpatialThing;" +
                                                            "rdfs:SubClassOf wd:Q33506;" +
                                                            "rdfs:label ?name;" +
                                                            "geo:long ?lat;" +
                                                            "geo:lat ?long ." +
                                                    "}";

    public static final String GET_UNIVERSITY_DATA =    "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                        "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                        "SELECT ?name ?lat ?long WHERE {" +
                                                                "?all  a geo:SpatialThing;" +
                                                                "rdfs:SubClassOf wd:Q3918;" +
                                                                "rdfs:label ?name;" +
                                                                "geo:long ?lat;" +
                                                                "geo:lat ?long ." +
                                                        "}";

    public static final String GET_HOSPITAL_DATA =  "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                    "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                    "SELECT ?name ?lat ?long WHERE {" +
                                                            "?name  a geo:SpatialThing;" +
                                                            "rdfs:SubClassOf wd:Q16917;" +
                                                            "geo:long ?lat;" +
                                                            "geo:lat ?long ." +
                                                    "}";

    public static final String GET_VELO_LYON_DATA = "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                    "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                    "SELECT ?name ?lat ?long WHERE {" +
                                                            "?all  a geo:SpatialThing;" +
                                                            "rdfs:SubClassOf wd:Q11442;" +
                                                            "rdfs:lable ?name;" +
                                                            "geo:long ?lat;" +
                                                            "geo:lat ?long ." +
                                                    "}";

    public static final String GET_VELO_MONTPELLIER_DATA =  "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                            "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                            "SELECT ?name ?lat ?long WHERE {" +
                                                                    "?all  a geo:SpatialThing;" +
                                                                    "rdfs:SubClassOf wd:Q11442;" +
                                                                    "rdfs:label ?name;" +
                                                                    "geo:long ?lat;" +
                                                                    "geo:lat ?long ." +
                                                            "}";

    public static final String GET_VELO_RENNES_DATA =   "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                        "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                        "SELECT ?name ?lat ?long WHERE {" +
                                                                "?all  a geo:SpatialThing;" +
                                                                "rdfs:SubClassOf wd:Q11442;" +
                                                                "rdfs:label ?name;" +
                                                                "geo:long ?lat;" +
                                                                "geo:lat ?long ." +
                                                        "}";

    public static final String GET_VELO_SAINT_ETIENNE_DATA =    "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                                "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                                "SELECT ?name ?lat ?long WHERE {" +
                                                                        "?all  a geo:SpatialThing;" +
                                                                        "rdfs:SubClassOf wd:Q11442;" +
                                                                        "rdfs:label ?name;" +
                                                                        "geo:long ?lat;" +
                                                                        "geo:lat ?long ." +
                                                                "}";

    public static final String GET_VELO_STRASBOURG_DATA =   "PREFIX geo: <http://www.opengis.net/ont/geosparql#>" +
                                                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                                                            "PREFIX wd: <http://www.wikidata.org/entity/>" +
                                                            "SELECT ?name ?lat ?long WHERE {" +
                                                                    "?all  a geo:SpatialThing;" +
                                                                    "rdfs:SubClassOf wd:Q11442;" +
                                                                    "rdfs:label ?name;" +
                                                                    "geo:long ?lat;" +
                                                                    "geo:lat ?long ." +
                                                            "}";

}
