package in.labulle.anycode.xmi.parser.jdom;

import static org.junit.Assert.*;
import in.labulle.anycode.uml.Cardinality;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClass;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IInterface;
import in.labulle.anycode.uml.IModel;
import in.labulle.anycode.uml.IOperation;
import in.labulle.anycode.uml.IPackage;
import in.labulle.anycode.uml.IRelationAttribute;
import in.labulle.anycode.xmi.parser.exception.XmiParserException;

import java.util.List;

import org.junit.Test;

public class XmiParserImplTest {

	@Test
	public void testSample01Model() {
		String file = getClass().getResource("/sample01-UML-2.1.1.xmi").getFile();
		XmiParserImpl parser = new XmiParserImpl(file);
		IModel model = parser.parse();
		assertEquals(1, model.getPackages().size());
		assertEquals(1, model.getClassifiers().size());
		assertEquals("Car", model.getClassifiers().get(0).getName());
		assertEquals("in", model.getPackages().get(0).getName());
		assertEquals("labulle", model.getPackages().get(0).getSubPackages().get(0).getName());
		IPackage p = model.getPackages().get(0).getSubPackages().get(0).getSubPackages().get(0);
		assertEquals("anycode", p.getName());
		List<IClassifier> classifiers = p.getClassifiers();
		assertEquals(3, classifiers.size());
		IClass c1 = (IClass) classifiers.get(0);
		assertEquals("Person", c1.getName());
		assertEquals(4, c1.getAttributes().size());
	}

	@Test
	public void testShapes() {
		String file = getClass().getResource("/shapes01.xmi").getFile();
		XmiParserImpl parser = new XmiParserImpl(file);
		IModel model = parser.parse();

		// Testing shape class.
		IClass shape = (IClass) model.findClassifierByFullyQualifiedName("in.labulle.anycode.sample.core.Shape", ".");
		assertEquals("Shape", shape.getName());
		assertTrue(shape.isAbstract());
		assertEquals(2, shape.getOperations().size());

		IOperation op1 = shape.getOperations().get(0);
		assertEquals("getPerimeter", op1.getName());
		assertEquals("double", op1.getReturnType().getName());

		IOperation op2 = shape.getOperations().get(1);
		assertEquals("getSurfaceArea", op2.getName());
		assertEquals("double", op2.getReturnType().getName());

		assertEquals(0, shape.getGeneralizations().size());
		assertEquals(1, shape.getRealizations().size());

		IInterface i = (IInterface) model.findClassifierByFullyQualifiedName("in.labulle.anycode.sample.core.Drawable", ".");
		assertEquals(i, shape.getRealizations().get(0));

		IClass polygon = (IClass) model.findClassifierByFullyQualifiedName("in.labulle.anycode.sample.core.Polygon", ".");
		assertEquals(1, polygon.getGeneralizations().size());
		assertEquals(shape, polygon.getGeneralizations().get(0));
		
		IRelationAttribute points = (IRelationAttribute) polygon.getAttributes().get(0);
		assertEquals(Cardinality.ONE_TO_MANY, points.getCardinality());
		assertEquals("points", points.getName());

		IClass circle = (IClass) model.findClassifierByFullyQualifiedName("in.labulle.anycode.sample.core.Circle", ".");
		assertEquals(2, circle.getAttributes().size());
		
		IClass point = (IClass) model.findClassifierByFullyQualifiedName("in.labulle.anycode.sample.core.Point", ".");

		IAttribute a1 = circle.getAttributes().get(0);
		assertEquals(point, a1.getDataType());
		
		assertEquals(2, point.getAttributes().size());
		
		
		IClass shapeService = (IClass) model.findClassifierByFullyQualifiedName("in.labulle.anycode.sample.service.ShapeService", ".");
		assertEquals(shape, shapeService.getClientDependencies().get(0));
		
		

	}

	@Test(expected = XmiParserException.class)
	public void testSample2NoXmlFile() {
		String file = getClass().getResource("/sample2.zargo").getFile();
		XmiParserImpl parser = new XmiParserImpl(file);
		assertNotNull(parser.parse());
	}

}
