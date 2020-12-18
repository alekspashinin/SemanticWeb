/**
 *
 *                      UJM * EMSE
 *
 *            Hamed RAHIMI * Aleksei PASHININ
 *
 *                 Semantic Web Project
 *
 */

package fr.emse.semwebfront.services;

import org.openrdf.model.IRI;
import org.openrdf.model.Model;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.SimpleValueFactory;

class ttlPart {

    private SimpleValueFactory factory = SimpleValueFactory.getInstance();
    private IRI iD;
    private Model model = new LinkedHashModel();

    ttlPart(String[] predicates, String[] data) {
        iD = factory.createIRI(data[0] + "#:#");
        for (int i = 0; i < predicates.length; i++) {
            model.add(iD, factory.createIRI("#:#" + predicates[i].substring(0, 1).toLowerCase() + predicates[i].substring(1)),
                    factory.createLiteral("<" + data[i + 1] + ">")
            );
        }
    }

    Model getModel() {
        return model;
    }
}
