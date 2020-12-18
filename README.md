# Geo Position Recommender with Semantic Web
In this Project, we have designed a recommender system that provides geo position of transport facilities such as Train, Bicycle, Bus and Tram in France. Besides, it contains social geo positions such Museums, Hospitals, Universities and Post Offices in France.
## Ontology
![ontology](RDF DATA/Images/ontology.bnp)
## Vocabulary 
```
@base <http://example/base/> .
@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
@prefix geo: <http://www.opengis.net/ont/geosparql#> .
@prefix foaf: <http://xmlns.com/foaf/0.1/> .
@prefix skos: <http://www.w3.org/2004/02/skos/core#> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix wd: <http://www.wikidata.org/entity/> .

<ID-Number> a geo:SpatialThing;
            rdfs:SubClassOf  <A>;
            rdfs:label      <Name>;
            wd:Q515         <Name_of_City>;
            geo:lat         <latitude>;
            geo:long        <longitude>.
<A> rdfs:SubClassOf <B> .
```
Sub Classes for A | Stands for
------------ | -------------
wd:Q129337 | TGV Stations
wd:Q13646 | SNCF Stations
wd:Q11442 | Bicycle Stops
wd:Q35054 | Post Offices 
wd:Q33506 | Museum Location
wd:Q16917  | Hospitals
wd:Q3918 | Universities

Sub Classes for B | Stands for
------------ | -------------
wd:Q870 | Transport Facilities
wd:Q41176 | Public Building
