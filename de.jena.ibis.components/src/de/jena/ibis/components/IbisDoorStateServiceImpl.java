/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.components;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.GeneralIbisTCPService;
import de.jena.ibis.apis.IbisDoorStateService;
import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.ibis.apis.constants.DoorStateServiceConstants;
import de.jena.ibis.components.helper.IbisTCPHelper;
import de.jena.ibis.model.doorstateservice.DoorOpenStatesResponse;
import de.jena.ibis.model.doorstateservice.DoorOperationStatesResponse;
import de.jena.ibis.model.doorstateservice.IbisDoorStateServicePackage;
import de.jena.ibis.model.doorstateservice.RetrieveSpecificDoorOpenStateResponse;
import de.jena.ibis.model.doorstateservice.RetrieveSpecificDoorOperationStateResponse;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.GeneralRetrieveRequest;
import de.jena.model.ibis.common.IbisCommonPackage;

/**
 * 
 * @author ilenia
 * @since Jun 28, 2023
 */
@Component(immediate=true, name = "IbisDoorStateService", 
service = {IbisDoorStateService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "DoorStateService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisDoorStateServiceImpl implements IbisDoorStateService {
	
	@Reference
	IbisDoorStateServicePackage doorStateServicePackage;
	
	@Reference 
	IbisCommonPackage ibisCommonPackage;
	
	@Reference(target = "(emf.resource.configurator.name=GeckoXMLResourceFactory)")
	private ComponentServiceObjects<ResourceSet> resourceSetFactory;
	
	private IbisTCPServiceConfig config;


	@Activate
	@Modified
	public void activate(IbisTCPServiceConfig config) throws ConfigurationException {
		IbisTCPHelper.checkTCPServiceConfig(config);
		this.config = config;
		executeAllSubscriptionOperations();
	}

	@Deactivate() 
	public void deactivate() {
		executeAllUnsubscriptionOperations();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#subscribeDoorOpenStates()
	 */
	@Override
	public void subscribeDoorOpenStates() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				DoorStateServiceConstants.OPERATION_SUBSCRIBE_DOOR_OPEN_STATES, 
				ibisCommonPackage, resourceSetFactory, true);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#unsubscribeDoorOpenStates()
	 */
	@Override
	public void unsubscribeDoorOpenStates() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				DoorStateServiceConstants.OPERATION_UNSUBSCRIBE_DOOR_OPEN_STATES, 
				ibisCommonPackage, resourceSetFactory, false);

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#subscribeDoorOperationStates()
	 */
	@Override
	public void subscribeDoorOperationStates() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				DoorStateServiceConstants.OPERATION_SUBSCRIBE_DOOR_OPERATION_STATES, 
				ibisCommonPackage, resourceSetFactory, true);

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#unsubscribeDoorOperationStates()
	 */
	@Override
	public void unsubscribeDoorOperationStates() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				DoorStateServiceConstants.OPERATION_UNSUBSCRIBE_DOOR_OPERATION_STATES, 
				ibisCommonPackage, resourceSetFactory, false);

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllGetOperations()
	 */
	@Override
	public List<GeneralResponse> executeAllGetOperations() {
		List<GeneralResponse> results = new ArrayList<>();
		DoorStateServiceConstants.getAllGetOperations().forEach(operation -> results.add(executeGetOperation(operation)));
		return results;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeGetOperation(java.lang.String)
	 */
	@Override
	public GeneralResponse executeGetOperation(String operation) {
		switch(operation) {
		case DoorStateServiceConstants.OPERATION_GET_DOOR_OPEN_STATES:
			return getDoorOpenStates();
		case DoorStateServiceConstants.OPERATION_GET_DOOR_OPERATION_STATES:
			return getDoorOperationStates();
		default:
			throw new IllegalArgumentException(String.format("Operation %s not implemented for %s!", operation, config.serviceName()));			
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeRetrieveOperation(java.lang.String, de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public GeneralResponse executeRetrieveOperation(String operation, GeneralRetrieveRequest request) {
		switch(operation) {
		case DoorStateServiceConstants.OPERATION_RETRIEVE_SPECIFIC_DOOR_OPEN_STATES:
			return retrieveSpecificDoorOpenState(request);
		case DoorStateServiceConstants.OPERATION_RETRIEVE_SPECIFIC_DOOR_OPERATION_STATES:
			return retrieveSpecificDoorOperationState(request);
		default:
			throw new IllegalArgumentException(String.format("Retrieve Operation %s not implemented for %s!", operation, config.serviceName()));			
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return config.serviceName();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceId()
	 */
	@Override
	public String getServiceId() {
		return config.serviceId();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceId()
	 */
	@Override
	public String getRefDeviceId() {
		return config.refDeviceId();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceType()
	 */
	@Override
	public String getRefDeviceType() {
		return config.refDeviceType();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllSubscriptionOperations()
	 */
	@Override
	public void executeAllSubscriptionOperations() {
		DoorStateServiceConstants.getAllSubscriptionOperations()
		.forEach(operation -> IbisTCPHelper.executeSubscriptionOperation(config, operation, ibisCommonPackage, resourceSetFactory, true));
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		DoorStateServiceConstants.getAllUnsubscriptionOperations()
		.forEach(operation -> IbisTCPHelper.executeSubscriptionOperation(config, operation, ibisCommonPackage, resourceSetFactory, false));
		
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#getDoorOpenStates()
	 */
	@Override
	public DoorOpenStatesResponse getDoorOpenStates() {
		return IbisTCPHelper.executeGetOperation(config, 
				DoorStateServiceConstants.OPERATION_GET_DOOR_OPEN_STATES, 
				doorStateServicePackage.getDoorOpenStatesResponse(),
				resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#getDoorOperationStates()
	 */
	@Override
	public DoorOperationStatesResponse getDoorOperationStates() {
		return IbisTCPHelper.executeGetOperation(config, 
				DoorStateServiceConstants.OPERATION_GET_DOOR_OPERATION_STATES, 
				doorStateServicePackage.getDoorOperationStatesResponse(),
				resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#retrieveSpecificDoorOpenState(de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public RetrieveSpecificDoorOpenStateResponse retrieveSpecificDoorOpenState(GeneralRetrieveRequest request) {
		return IbisTCPHelper.executeRetrieveOperation(config, 
				DoorStateServiceConstants.OPERATION_RETRIEVE_SPECIFIC_DOOR_OPEN_STATES, 
				request, doorStateServicePackage.getRetrieveSpecificDoorOpenStateResponse(), resourceSetFactory);	
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDoorStateService#retrieveSpecificDoorOperationState(de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public RetrieveSpecificDoorOperationStateResponse retrieveSpecificDoorOperationState(
			GeneralRetrieveRequest request) {
		return IbisTCPHelper.executeRetrieveOperation(config, 
				DoorStateServiceConstants.OPERATION_RETRIEVE_SPECIFIC_DOOR_OPERATION_STATES, 
				request, doorStateServicePackage.getRetrieveSpecificDoorOperationStateResponse(), resourceSetFactory);	
	}

}
