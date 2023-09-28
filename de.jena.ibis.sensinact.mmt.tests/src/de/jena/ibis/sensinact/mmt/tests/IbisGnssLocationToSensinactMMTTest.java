/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.sensinact.mmt.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Map;

import org.gecko.core.pool.Pool;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithConfiguration;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import de.jena.ibis.gnsslocationservice.GNSSLSFactory;
import de.jena.ibis.gnsslocationservice.GNSSLocationServiceDataStructure;
import de.jena.ibis.sensinact.mmt.tests.helper.IbisToSensinactTestHelper;
import de.jena.model.ibis.enumerations.GNSSCoordinateSystemEnumeration;
import de.jena.model.ibis.enumerations.GNSSQualityEnumeration;
import de.jena.model.ibis.enumerations.GNSSTypeEnumeration;
import de.jena.model.sensinact.ibis.IbisDevice;


/**
 * See documentation here: 
 * 	https://github.com/osgi/osgi-test
 * 	https://github.com/osgi/osgi-test/wiki
 * Examples: https://github.com/osgi/osgi-test/tree/main/examples
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
public class IbisGnssLocationToSensinactMMTTest {

	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=gnsslocationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testAllData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		GNSSLocationServiceDataStructure data = GNSSLSFactory.eINSTANCE.createGNSSLocationServiceDataStructure();
		data.setLatitude(IbisToSensinactTestHelper.createIbisGNSSCoordinates(78, "north"));
		data.setLongitude(IbisToSensinactTestHelper.createIbisGNSSCoordinates(46, "east"));
		data.setAltitude(IbisToSensinactTestHelper.createIbisDouble(300));
		data.setDate(IbisToSensinactTestHelper.createIbisDate(new Date()));
		data.setGNSSCoordinateSystem(GNSSCoordinateSystemEnumeration.IERS);
		data.setGNSSType(GNSSTypeEnumeration.GALILEO);
		data.setHoriziontalDilutionOfPrecision(IbisToSensinactTestHelper.createIbisDouble(7.5));
		data.setVerticalDilutionOfPrecision(IbisToSensinactTestHelper.createIbisDouble(18.7));
		data.setNumberOfSatellites(IbisToSensinactTestHelper.createIbisInt(7));
		data.setSignalQuality(GNSSQualityEnumeration.GPS);
		data.setSpeedOverGround(IbisToSensinactTestHelper.createIbisDouble(50.3));
		data.setTime(IbisToSensinactTestHelper.createIbisTime(new Date()));
		data.setTrackDegreeMagnetic(IbisToSensinactTestHelper.createIbisDouble(3.2));
		data.setTrackDegreeTrue(IbisToSensinactTestHelper.createIbisDouble(4.5));
		
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(data);
		assertThat(sensinactDevice).isNotNull();
		
		de.jena.model.sensinact.ibis.GNSSLocationData sensinactData = sensinactDevice.getGnssLocationData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getLatitudeDegree()).isEqualTo(78);
		assertThat(sensinactData.getLongitudeDegree()).isEqualTo(46);
		assertThat(sensinactData.getLatitudeDirection()).isEqualTo("north");
		assertThat(sensinactData.getLongitudeDirection()).isEqualTo("east");
		assertThat(sensinactData.getAltitude()).isEqualTo(300);
		assertThat(sensinactData.getDate()).isNotNull();
		assertThat(sensinactData.getGNSSCoordinateSystem()).isEqualTo(GNSSCoordinateSystemEnumeration.IERS.getLiteral());
		assertThat(sensinactData.getGNSSType()).isEqualTo(GNSSTypeEnumeration.GALILEO.getLiteral());
		assertThat(sensinactData.getHorizontalDilutionOfPrecision()).isEqualTo(7.5);
		assertThat(sensinactData.getVerticalDilutionOfPrecision()).isEqualTo(18.7);
		assertThat(sensinactData.getNumberOfSatellites()).isEqualTo(7);
		assertThat(sensinactData.getSignalQuality()).isEqualTo(GNSSQualityEnumeration.GPS.getLiteral());
		assertThat(sensinactData.getTime()).isNotNull();
		assertThat(sensinactData.getTrackDegreeMagnetic()).isEqualTo(3.2);
		assertThat(sensinactData.getTrackDegreeTrue()).isEqualTo(4.5);	
		assertThat(sensinactData.getSpeedOverGround()).isEqualTo(50.3);

		assertThat(sensinactData.getMetadata()).isNotEmpty();
		assertThat(sensinactData.getMetadata()).hasSize(16);		
	}
	
	
}
