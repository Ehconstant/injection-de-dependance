package injectionxml;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import org.jdom2.input.SAXBuilder;


public class Injecte {

	static Document doc ;
	static Element elmtparent, elmtenf, injection;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		// Ouverture et lecture du fichier.xml avec SAXBuilder
		SAXBuilder sax = new SAXBuilder();
		
		try
		{
			doc = sax.build(new File("fichier.xml"));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		elmtparent = doc.getRootElement();
		elmtenf = elmtparent.getChild("injection");
		
		// recuperation du lien de la classe 
		String classdir = System.getProperty("user.dir");
		String classpath = classdir.substring(classdir.lastIndexOf("\\")+1, classdir.length());
		classpath = classpath +"."+ elmtenf.getAttributeValue("classe");
		
		
		// chargement des classe, à partir des nons recuperer dans le fichier Xml
		Class<?> c = Class.forName(classpath);
		Class<?> d = Class.forName(elmtenf.getAttributeValue("inject"));

		//,appel du constructeur
		Constructor<?> ctr = c.getConstructor(new Class[]{List.class});
		
		// Creation d'une instance avec le type choisie
		Pile<Integer> p = (Pile<Integer>) ctr.newInstance(d.newInstance());
		
		// Empilage 
		p.empiler(1);
		p.empiler(12);
		p.empiler(3);
		p.empiler(20);
		
		// Depilage
		try {
			System.out.println(p.depiler());
			System.out.println(p.depiler());
			System.out.println(p.depiler());
			System.out.println(p.depiler());
			System.out.println(p.depiler());
		}catch(IndexOutOfBoundsException  e) {
			System.out.println("Pile vide");
		}
		
	}

}
