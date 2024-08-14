/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.gecko.emf.osgi.annotation.require.RequireEMF;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.enumerations.IbisEnumerationsPackage;
import de.jena.model.ibis.gnsslocationservice.GNSSLocationData;
import de.jena.model.ibis.gnsslocationservice.IbisGNSSLocationServicePackage;
import de.jena.model.ibis.gnsslocationservice.util.IbisGNSSLocationServiceResourceFactoryImpl;

@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@RequireEMF
public class IbisGNSSTest {
	@Test
	public void testReadXMLGNSSFromFile(@InjectService(timeout = 1000) ServiceAware<ResourceSet> resourceSetAware)
			throws Exception {
		assertThat(resourceSetAware).isNotNull();
		ResourceSet resourceSet = resourceSetAware.getService();
		assertThat(resourceSet).isNotNull();
		initResourceSet(resourceSet);

		URI uri = URI.createFileURI(System.getProperty("base.path") + "/data/gnss.xml");
		Resource requestRes = resourceSet.createResource(uri, "application/xml");
		Map<String, Object> options = new HashMap<>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData() {
			@Override
			public EPackage getPackage(String namespace) {
				if (Objects.isNull(namespace)) {
					return resourceSet.getPackageRegistry().getEPackage(IbisGNSSLocationServicePackage.eNS_URI);
				}
				return super.getPackage(namespace);
			}

			@Override
			protected boolean isFeatureNamespaceMatchingLax() {
				return Boolean.TRUE;
			}
		});
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
		requestRes.load(options);
		assertThat(requestRes.getContents()).isNotEmpty();
		EObject obj = requestRes.getContents().get(0);
		assertThat(obj instanceof GNSSLocationData).isTrue();
	}

	private void initResourceSet(ResourceSet set) {
		set.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		set.getPackageRegistry().put(XMLTypePackage.eNS_URI, XMLTypePackage.eINSTANCE);
		set.getPackageRegistry().put(XMLNamespacePackage.eNS_URI, XMLNamespacePackage.eINSTANCE);
		set.getPackageRegistry().put(IbisGNSSLocationServicePackage.eNS_URI, IbisGNSSLocationServicePackage.eINSTANCE);
		set.getPackageRegistry().put(IbisEnumerationsPackage.eNS_URI, IbisEnumerationsPackage.eINSTANCE);
		set.getPackageRegistry().put(IbisCommonPackage.eNS_URI, IbisCommonPackage.eINSTANCE);
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("gnsslocation",
				new IbisGNSSLocationServiceResourceFactoryImpl());
	}

}
