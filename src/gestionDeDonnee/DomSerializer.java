package gestionDeDonnee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * Copié des exercices d'XML
 * 
 * @author
 *
 */
public class DomSerializer {

	private Document document;

	public DomSerializer(Document document) {
		super();
		this.document = document;
	}

	@SuppressWarnings("unused")
	private void __test_serialize() throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File("output.xml"));
		Source input = new DOMSource(this.document);

		transformer.transform(input, output);
	}

	public void serialize(String filename) throws IOException, ClassCastException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		// teste la compatibilit� DOM3
		DOMImplementationLS implLS = null;
		if (document.getImplementation().hasFeature("LS", "3.0")) {
			implLS = (DOMImplementationLS) document.getImplementation();
		} else {
			DOMImplementationRegistry implementation = DOMImplementationRegistry.newInstance();
			implLS = (DOMImplementationLS) implementation.getDOMImplementation("LS 3.0");
		}
		if (implLS == null) {
			System.out.println("API DOM Load and Save non support�e");
			return;
		}

		// Ecriture du document
		LSSerializer serializer = implLS.createLSSerializer();
		LSOutput output = implLS.createLSOutput();
		FileOutputStream writer = new FileOutputStream(filename);
		output.setByteStream(writer);
		serializer.getDomConfig().setParameter("format-pretty-print", true);
		serializer.write(document, output);
	}
}
